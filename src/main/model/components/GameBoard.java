package model.components;


import exception.*;
import model.pieces.*;

import static model.components.PieceFactory.*;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

import static model.pieces.PieceClass.*;

/**
 * Represents XiangQi game board with players on Black and Red sides
 * Coordinate (x, y) start with (0,0) on the bottom left, increasing to the right and up
 * The River is between row 4 and 5, certain pieces may not cross the river
 */
public class GameBoard implements Writable {
    Player red;
    Player black;
    String gameLog;
    HashMap<String, Piece> board;

    public static final int MAX_X_COORD = 8;
    public static final int MIN_X_COORD = 0;
    public static final int MAX_Y_COORD = 9;
    public static final int MIN_Y_COORD = 0;

    // EFFECTS: instantiate a GameBoard
    public GameBoard() {
        red = new Player(true);
        black = new Player(false);
        board = new HashMap<>();
        gameLog = "";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        json.put("pieces", piecesToJson());
        return json;
    }

    // EFFECTS: returns players as a JSON array
    public JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(red.toJson());
        jsonArray.put(black.toJson());
        return jsonArray;
    }

    // EFFECTS: returns all pieces on board as a JSON array
    private JSONArray piecesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Piece p : red.getPieces()) {
            jsonArray.put(p.toJson());
        }
        for (Piece p : black.getPieces()) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // REQUIRES: a game has been set up
    // EFFECTS: return true if <= 1 general's left on the board
    public boolean checkWin() throws IllegalNumGeneralException {
        int generalCount = 0;
        for (Piece p : board.values()) {
            if (p.getPieceClass().equals(GENERAL)) {
                generalCount++;
            }
        }
        if (generalCount > 2) {
            throw new IllegalNumGeneralException();
        }
        return generalCount < 2;
    }

    // REQUIRES: pos is a valid position within range ([MIN_X_COORD, MAX_X_COORD], [MIN_Y_COORD, MAX_Y_COORD])
    // EFFECTS: checks if the given position (x, y) is occupied by any piece
    public boolean isEmptyAt(int x, int y) {
        return !board.containsKey(toStrLoc(x, y));
    }

    // MODIFIES: this
    // EFFECTS: clear all pieces on the board and from the players
    public void clearBoard() {
        board = new HashMap<>();
        red.clear();
        black.clear();
    }

    // EFFECTS: returns a string representation of the status of this board that includes
    //          positions of all pieces on board with red pieces first
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for (Piece p : red.getPieces()) {
            strBuilder.append(p.toString());
            strBuilder.append(" ");
        }
        strBuilder.append("\n");
        for (Piece p : black.getPieces()) {
            strBuilder.append(p.toString());
            strBuilder.append(" ");
        }
        return strBuilder.toString().trim();
    }

    // MODIFIES: this
    // EFFECT: place given piece onto the correct position on board and add to player
    public void placePiece(Piece p) throws LocationOccupiedException {
        if (!isEmptyAt(p.getPosX(), p.getPosY())) {
            throw new LocationOccupiedException();
        }
        if (! board.containsValue(p)) {
            if (p.isRed()) {
                red.addPiece(p);
            } else {
                black.addPiece(p);
            }
        }
        updatePieceLocation(p);
        assert p == board.get(toStrLoc(p.getPosX(), p.getPosY()));
    }

    // MODIFIES: this
    // EFFECTS: add a captured pieces to player specified by isRed
    public void addCapturedPiece(String pc, int x, int y, boolean capturedByRed) throws IllegalInputException {
        if (capturedByRed) {
            red.capture(makeNewPiece(pc.toLowerCase(), x, y, null, false));
        } else {
            black.capture(makeNewPiece(pc.toLowerCase(), x, y, null, true));
        }
    }

    // EFFECTS: return the piece on the given position or null if the position isn't occupied
    public Piece getPAt(int x, int y) {
        return board.get(toStrLoc(x, y));
    }

    // getters
    public Player getRed() {
        return red;
    }

    public Player getBlack() {
        return black;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameBoard gameBoard = (GameBoard) o;
        return red.equals(gameBoard.red)
                && black.equals(gameBoard.black)
                && gameLog.equals(gameBoard.gameLog)
                && board.equals(gameBoard.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, black, gameLog, board);
    }

    // MODIFIES: this, moving, other
    // EFFECTS: moves piece at (fromX, fromY) to (toX, toY) and captures any opponent piece if possible
    //          throws IllegalInputException if given move cannot be performed
    public void makeMove(int fromX, int toX, int fromY, int toY, boolean redMoving)
            throws IllegalInputException {
        if (isEmptyAt(fromX, fromY)) {
            throw new IllegalInputException();
        }
        Player playing = (redMoving ? red : black);
        Player other = (redMoving ? black : red);
        Piece p = getPAt(fromX, fromY);
        if (p.isRed() == redMoving) {
            if (isEmptyAt(toX, toY)) {
                if (p.canMoveTo(toX, toY)) {
                    movePiece(p, toX, toY);
                    return;
                }
            } else {
                Piece prey = getPAt(toX, toY);
                if (prey.isRed() != redMoving && p.canCapture(toX, toY)) {
                    capture(p, prey, playing, other);
                    return;
                }
            }
        }
        throw new IllegalInputException();
    }

    // MODIFIES: this
    // EFFECTS: update the board's information to reflect the piece's location
    public void updatePieceLocation(Piece piece) {
        String keyToRemove = null;
        for (String key : board.keySet()) {
            if (piece.equals(board.get(key))) {
                if (toStrLoc(piece.getPosX(), piece.getPosY()).equals(key)) {
                    return;
                } else {
                    keyToRemove = key;
                    break;
                }
            }
        }
        board.remove(keyToRemove);
        board.put(toStrLoc(piece.getPosX(), piece.getPosY()), piece);
    }

    // REQUIRES: hunter is a piece owned by playing and prey is a piece owned by other
    // MODIFIES: this, hunter, playing, other
    // EFFECTS: playing captures p from other, removing p from the board and moving hunter to prey's position
    private void capture(Piece hunter, Piece prey, Player playing, Player other) throws LocationOccupiedException {
        prey.remove();
        removePieceFromBoard(prey.getPosX(), prey.getPosY());
        playing.capture(prey);
        other.removePiece(prey);
        movePiece(hunter, prey.getPosX(), prey.getPosY());
    }

    // REQUIRES: given piece can be moved to given coordinate (x, y)
    // MODIFIES: this, p
    // EFFECTS: move p to location (x, y)
    private void movePiece(Piece p, int x, int y) throws LocationOccupiedException {
        p.move(x, y);
        updatePieceLocation(p);
    }

    // REQUIRES: position (x, y) is a valid position on the board and occupied by a piece
    // MODIFIES: this
    // EFFECTS: vacant the given spot
    void removePieceFromBoard(int x, int y) {
        board.remove(toStrLoc(x, y));
    }

    // EFFECTS: produce a string version of x*10 + y
    private String toStrLoc(int x, int y) {
        return x + String.valueOf(y);
    }
}
