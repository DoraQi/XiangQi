package model.pieces;


import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

/**
 * A generic Piece of XiangQi
 */
public abstract class Piece implements Writable {
    private int posX;
    private int posY;
    protected GameBoard board;
    private final boolean isRed;

    private final PieceClass pieceClass;

    // EFFECTS: constructs elements common to all Piece
    public Piece(int x, int y, boolean isRed, GameBoard b, PieceClass c)
            throws OutOfBoundPositionException, LocationOccupiedException {
        if (x > GameBoard.MAX_X_COORD || x < GameBoard.MIN_X_COORD
                || y > GameBoard.MAX_Y_COORD || y < GameBoard.MIN_Y_COORD) {
            throw new OutOfBoundPositionException();
        }
        posX = x;
        posY = y;
        board = b;
        this.isRed = isRed;
        if (b != null) {
            b.placePiece(this);
        }
        this.pieceClass = c;
    }

    // REQUIRES: this piece can move to the given coordinate and that it's empty on board
    // MODIFIES: this
    // EFFECTS: move this piece from current position to target position
    public void move(int x, int y) throws LocationOccupiedException {
        if (x != posX || y != posY) {
            posX = x;
            posY = y;
            board.updatePieceLocation(this);
        }
    }

    // REQUIRES: given coordinate is a valid position on the board and occupied by an opponent's piece
    // EFFECTS: return true if the piece on the given coordinate can be captured by this piece
    public boolean canCapture(int x, int y) {
        return canMoveTo(x, y);
    }

    // REQUIRES: (x, y) to is a valid position on the board and is empty
    // EFFECTS: produce true if this piece can move to target location, false otherwise
    public abstract boolean canMoveTo(int x, int y);

    // EFFECTS: returns the Y position of this piece
    public int getPosY() {
        return posY;
    }

    // EFFECTS: returns the X position of this piece
    public int getPosX() {
        return posX;
    }

    // EFFECTS: returns true if this piece is on the red side, false otherwise
    public boolean isRed() {
        return isRed;
    }

    // EFFECTS: return a string representation of this piece in the format:
    //          "Class [x, y]R/B"
    //          ex. "Soldier [3, 4]R"
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(pieceClass.name().charAt(0));
        strBuilder.append(pieceClass.name().substring(1).toLowerCase());
        strBuilder.append("[").append(posX).append(", ").append(posY).append("]");
        if (isRed) {
            strBuilder.append("R");
        } else {
            strBuilder.append("B");
        }
        return strBuilder.toString();
    }

    // EFFECTS: return a string specifying the class of the piece
    public PieceClass getPieceClass() {
        return pieceClass;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pieceClass", pieceClass);
        json.put("posX", posX);
        json.put("posY", posY);
        if (isRed()) {
            json.put("isRed", "r");
        } else {
            json.put("isRed", "b");
        }

        return json;
    }

    // MODIFIES: this
    // EFFECTS: remove board from this piece
    public void remove() {
        board = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return posX == piece.posX
                && posY == piece.posY
                && isRed == piece.isRed
                && pieceClass == piece.pieceClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY, isRed, pieceClass);
    }
}
