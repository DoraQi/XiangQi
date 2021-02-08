package model.components;

import javafx.util.Pair;
import model.pieces.General;
import model.pieces.Piece;

import java.util.HashMap;

/**
 * Represents a game of XiangQi with the board and players on Black and Red sides
 * Coordinate (x, y) start with (0,0) on the bottom right, increasing to the right and up
 * The River is between row 4 and 5, certain pieces may not cross the river
 */
public class GameBoard {
    Player red;
    Player black;
    String gameLog;

    HashMap<String, Piece> board; // Change to Map


    private static final int MAX_X_COORD = 8;
    private static final int MIN_X_COORD = 0;
    private static final int MAX_Y_COORD = 9;
    private static final int MIN_Y_COORD = 0;

    // EFFECTS: instantiate a GameBoard
    public GameBoard() {
        red = new Player(true);
        black = new Player(false);
        board = new HashMap<>();
        gameLog = "";
    }

    // REQUIRES: a game has been set up
    // EFFECTS: return true if a player has won
    public boolean checkWin() {
        return false;
    }

//    // EFFECTS: print out messages to end the game
//    public void endGame() {
//    }
//
//    // MODIFIES: p, this
//    // EFFECTS: prompt for player p to make the move, and make move
//    public void play(Player p) {
//    }

    // REQUIRES: pos is a valid position within range ([MIN_X_COORD, MAX_X_COORD], [MIN_Y_COORD, MAX_Y_COORD])
    // EFFECTS: checks if the given position (x, y) is occupied by any piece
    public boolean isEmpty(int x, int y) {
        return !board.containsKey(toStringLoc(x, y));
    }


    // REQUIRES: hunter is a piece owned by playing and prey is a piece owned by other
    // MODIFIES: playing, other
    // EFFECTS: playing captures p from other, removing p from the board and moving hunter to prey's position
    public void capture(Piece hunter, Piece prey, Player playing, Player other) {
        removePiece(prey.getPosX(), prey.getPosY());
        playing.capture(prey);
        other.removePiece(prey);
        hunter.move(prey.getPosX(), prey.getPosY());
    }

    // REQUIRES: given piece can be moved to given coordinate (x, y)
    // MODIFIES: this, p
    // EFFECTS: move p to location (x, y)
    public void movePiece(Piece p, int x, int y) {
        // remove p from board
        removePiece(p.getPosX(), p.getPosY());
        // update position in p
        p.move(x, y);
        // update position on board
        placePiece(p);
    }

    // EFFECTS: prints all pieces on board with their positions
    public String toString() {
        return null;
    }

    // REQUIRES: position of p is currently empty on this board
    // MODIFIES: this
    // EFFECT: place given piece onto the correct position on board
    public void placePiece(Piece p) {
        board.put(toStringLoc(p.getPosX(), p.getPosY()), p);
    }

    // REQUIRES: given coordinate (x, y) is a valid position on board,
    //           given position is occupied by a Piece
    // EFFECTS: return the piece on the given position
    public Piece getPAt(int x, int y) {
        return board.get(toStringLoc(x, y));
    }

    // MODIFIES: this
    // EFFECTS: sets up the board and players to play the game
    public void setUpClassicGame() {
        makeSoldiers();
        makeChariots();
        makeCannons();
        makeElephants();
        makeHorses();
        makeGenerals();
    }

    // REQUIRES: the given array must be of length 4 that specifies a valid location on board
    // MODIFIES: this
    // EFFECTS: move the piece at given location to the specified location and return true if successful
    public boolean blackMove(int[] move) {
        return playerMove(move, black, red);
//        int fromX = move[0];
//        int fromY = move[1];
//        int toX = move[2];
//        int toY = move[3];
//        if (isEmpty(fromX, fromY)) {
//            return false;
//        }
//        Piece p = getPAt(fromX, fromY);
//        if (!p.isRed()) {
//            if (isEmpty(toX, toY)) {
//                if (p.canMoveTo(toX, toY)) {
//                    movePiece(p, toX, toY);
//                    return true;
//                }
//            } else {
//                Piece prey = getPAt(toX, toY);
//                if (prey.isRed() && p.canCapture(toX, toY)) {
//                    capture(p, prey, black, red);
//                    return true;
//                }
//            }
//        }
//        return false;
    }

    // REQUIRES: the given array must be of length 4 that specifies a valid location on board
    // MODIFIES: this
    // EFFECTS: move the piece at given location to the specified location and return true if successful
    public boolean redMove(int[] move) {
        return playerMove(move, red, black);
//        int fromX = move[0];
//        int fromY = move[1];
//        int toX = move[2];
//        int toY = move[3];
//        if (isEmpty(fromX, fromY)) {
//            return false;
//        }
//        Piece p = getPAt(fromX, fromY);
//        if (p.isRed()) {
//            if (isEmpty(toX, toY)) {
//                if (p.canMoveTo(toX, toY)) {
//                    movePiece(p, toX, toY);
//                    return true;
//                }
//            } else {
//                Piece prey = getPAt(toX, toY);
//                if (!prey.isRed() && p.canCapture(toX, toY)) {
//                    capture(p, prey, red, black);
//                    return true;
//                }
//            }
//        }
//        return false;
    }

    // REQUIRES: move has a length of 4 that specifies a valid location on board,
    //           moving is the player making the move, other is the other player
    // MODIFIES: this, moving, other
    // EFFECTS: player playing makes the given move and return true if successful, false if not
    private boolean playerMove(int[] move, Player moving, Player other) {
        int fromX = move[0];
        int fromY = move[1];
        int toX = move[2];
        int toY = move[3];
        if (isEmpty(fromX, fromY)) {
            return false;
        }
        Piece p = getPAt(fromX, fromY);
        if (p.isRed() == moving.isRed()) {
            if (isEmpty(toX, toY)) {
                if (p.canMoveTo(toX, toY)) {
                    movePiece(p, toX, toY);
                    return true;
                }
            } else {
                Piece prey = getPAt(toX, toY);
                if (prey.isRed() == other.isRed() && p.canCapture(toX, toY)) {
                    capture(p, prey, moving, other);
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeGenerals() {
        General redG = new General(4, 0, this, true);
        General blackG = new General(4, 9, this, false);
        placePiece(redG);
        placePiece(blackG);
        red.addPiece(redG);
        black.addPiece(blackG);
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeHorses() {
        // create 4 horses
        // add each to board
        // add each to player
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeElephants() {
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeCannons() {
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeChariots() {
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeSoldiers() {
    }

    // REQUIRES: position (x, y) is a valid position on the board
    // MODIFIES: this
    // EFFECTS: vacant the given spot
    private void removePiece(int x, int y) {
        board.remove(toStringLoc(x, y));
    }

    private String toStringLoc(int x, int y) {
        return String.valueOf(x) + String.valueOf(y);
    }

    public static int getMaxXCoord() {
        return MAX_X_COORD;
    }

    public static int getMinXCoord() {
        return MIN_X_COORD;
    }

    public static int getMaxYCoord() {
        return MAX_Y_COORD;
    }

    public static int getMinYCoord() {
        return MIN_Y_COORD;
    }
}
