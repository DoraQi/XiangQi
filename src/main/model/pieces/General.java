package model.pieces;

public class General extends Advisor {


    public General(int x, int y, boolean side) {
        super(x, y, side);
    }

    //
    @Override
    public boolean canMoveTo(int x, int y) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: move position to to, captures opponent general if aligned
    @Override
    public void move(int x, int y) { }
}
