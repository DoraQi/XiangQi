package model.pieces;

import model.GameBoard;

/**
 * Represents a General piece of a game of XiangQi
 */
public class General extends Piece {


    public General(int x, int y, boolean side) {
        super(x, y, side, "General");
    }

    //
    @Override
    public boolean canMoveTo(int x, int y, GameBoard b) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: move position to to, captures opponent general if aligned
    @Override
    public void move(int x, int y) { }
}
