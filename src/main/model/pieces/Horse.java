package model.pieces;

public class Horse extends Piece {

    public Horse(int x, int y, boolean side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        return false;
    }
}
