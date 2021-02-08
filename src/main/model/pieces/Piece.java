package model.pieces;

import model.components.GameBoard;

public abstract class Piece {
    private int posX;
    private int posY;
    protected GameBoard board;
    private final boolean redSide;

    private String pieceClass;

    protected Piece(int x, int y, boolean redSide, GameBoard b, String c) {
        move(x, y);
        board = b;
        b.placePiece(this);
        this.redSide = redSide;
        this.pieceClass = c;
    }


    // REQUIRES: this piece can move to the given coordinate
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

    // REQUIRES: (x, y) to is a valid position on the board
    // EFFECTS: produce true if this piece can move to target location
    public abstract boolean canMoveTo(int x, int y);

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public boolean isRed() {
        return redSide;
    }

    // Need test????
    public String toString() {
        String str = pieceClass + "[" + posX + ", " + posY + "]";
        if (redSide) {
            str += "R";
        } else {
            str += "B";
        }
        return str;
    }

    public String getPieceClass() {
        return pieceClass;
    }
}
