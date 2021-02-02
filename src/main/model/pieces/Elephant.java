package model.pieces;

import model.GameBoard;

/**
 * Represents an Elephant piece of a game of XiangQi
 */
public class Elephant extends Piece {

    public Elephant(int x, int y, boolean isRed) {
        super(x, y, isRed, "Elephant");
    }

    @Override
    public boolean canMoveTo(int x, int y, GameBoard b) {
        return false;
    }
}
