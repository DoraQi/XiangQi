package model.pieces;

public class Elephant extends Piece {

    public Elephant(int x, int y, boolean isRed) {
        super(x, y, isRed);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        return false;
    }
}
