package model.pieces;

import model.GameBoard;

/**
 * Represents an Advisor piece of a game of XiangQi
 */
public class Advisor extends Piece {
    private final int MIN_X;
    private final int MAX_X;
    private final int MIN_Y;
    private final int MAX_Y;

    // REQUIRES: given coordinates are a valid position on the diagonal of the palace
    // EFFECTS: instantiate an Advisor with given positions and side
    public Advisor(int x, int y, boolean isRed) {
        super(x, y, isRed, "Advisor");
        MIN_X = 3;
        MAX_X = 5;
        if (isRed) {
            MIN_Y = 0;
            MAX_Y = 2;
        } else {
            MIN_Y = 7;
            MAX_Y = 9;
        }
    }

    // REQUIRES: given coordinate (x, y) are valid coordinates on the board
    // EFFECTS: produce true if this can move to the given coordinate
    @Override
    public boolean canMoveTo(int x, int y, GameBoard b) {
        if (x >= MIN_X && x <= MAX_X && y <= MAX_Y && y >= MIN_Y) {
            return Math.abs(getPosY() - y) == 1 && Math.abs(getPosX() - x) == 1;
        }
        return false;
    }
}
