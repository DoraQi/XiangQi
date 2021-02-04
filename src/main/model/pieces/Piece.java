package model.pieces;

import model.GameBoard;

public abstract class Piece {
    private int posX;
    private int posY;
    private final boolean redSide;

    private String pieceClass;

    protected Piece(int x, int y, boolean redSide, String c) {
        move(x, y);
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

    // REQUIRES: given coordinate is a valid position on the board
    // EFFECTS: return true if any piece on the given coordinate can be captured by this piece
    public boolean canCapture(int x, int y, GameBoard b) {
        return canMoveTo(x, y, b);
    }

    // REQUIRES: (x, y) to is a valid position on the board
    // EFFECTS: produce true if this piece can move to target location
    public abstract boolean canMoveTo(int x, int y, GameBoard b);

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public boolean isRed() {
        return redSide;
    }

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
