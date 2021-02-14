package model.pieces;

import model.components.GameBoard;

/**
 * A generic Piece of XiangQi
 */
public abstract class Piece {
    private int posX;
    private int posY;
    protected GameBoard board;
    private final boolean redSide;

    private final String pieceClass;

    // REQUIRES: given (x, y) is a valid empty position on board b
    // EFFECTS: constructs elements common to all Piece
    public Piece(int x, int y, boolean redSide, GameBoard b, String c) {
        move(x, y);
        board = b;
        b.placePiece(this);
        this.redSide = redSide;
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
        return redSide;
    }

    // EFFECTS: return a string representation of this piece in the format:
    //          "Class [x, y]R/B"
    //          ex. "Soldier [3, 4]R"
    public String toString() {
        String str = pieceClass + "[" + posX + ", " + posY + "]";
        if (redSide) {
            str += "R";
        } else {
            str += "B";
        }
        return str;
    }

    // EFFECTS: return a string specifying the class of the piece
    public String getPieceClass() {
        return pieceClass;
    }
}
