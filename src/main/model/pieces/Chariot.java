package model.pieces;

public class Chariot extends Piece {

    public Chariot(int x, int y, boolean side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        return false;
    }
}
