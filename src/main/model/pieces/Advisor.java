package model.pieces;

public class Advisor extends Piece {


    public Advisor(int x, int y, boolean isRed) {
        super(x, y, isRed);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        return false;
    }


}
