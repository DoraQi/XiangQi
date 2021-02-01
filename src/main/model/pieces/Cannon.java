package model.pieces;

public class Cannon extends Piece {

    public Cannon(int x, int y, boolean isRed) {
        super(x, y, isRed);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        return false;
    }

    // REQUIRES: given coordinates are valid positions on the board
    // EFFECTS: produce true iff this piece is aligned horizontally or vertically and
    //          there is 1 other piece between this piece and the given coordinate
    @Override
    public boolean canCapture(int x, int y) { return false; }
}
