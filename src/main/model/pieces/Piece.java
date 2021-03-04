package model.pieces;


import model.components.GameBoard;
import org.json.JSONObject;
import persistence.Writable;

/**
 * A generic Piece of XiangQi
 */
public abstract class Piece implements Writable {
    private int posX;
    private int posY;
    protected GameBoard board;
    private final boolean isRed;

    private final PieceClass pieceClass;

    // REQUIRES: b is a valid board or null for a captured piece.
    //           given (x, y) is a valid empty position on board b
    // EFFECTS: constructs elements common to all Piece
    public Piece(int x, int y, boolean isRed, GameBoard b, PieceClass c) {
        move(x, y);
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
    public void move(int x, int y) {
        posX = x;
        posY = y;
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

    // EFFECTS: return true if every field of this piece is the same as the other piece, else return false
    public boolean equals(Piece other) {
        return this.pieceClass.equals(other.pieceClass) && this.posX == other.posX && this.posY == other.posY
                && this.isRed() == other.isRed();
    }

}
