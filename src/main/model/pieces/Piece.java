package model.pieces;

import model.GameBoard;

public abstract class Piece {
    private int posX;
    private int posY;
    private final boolean side;

    private String pieceClass;

    public Piece(int x, int y, boolean side, String c) {
        move(x, y);
        this.side = side;
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

    // REQUIRES: to is a square on the board
    // EFFECTS: produce true if this piece can move to target location
    public abstract boolean canMoveTo(int x, int y, GameBoard b);

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public boolean isRed() {
        return side;
    }

    public String toString() {
        String str = pieceClass + "[" + posX + ", " + posY + "]";
        if (side) {
            str += "R";
        } else {
            str += "B";
        }
        return str;
    }
}
