package model.pieces;

import model.components.GameBoard;

/**
 * Represents an Advisor piece of a game of XiangQi
 */
public class Advisor extends Piece {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    // REQUIRES: given coordinates are a valid position on the diagonal of the palace
    // EFFECTS: instantiate an Advisor with given positions and side
    public Advisor(int x, int y, GameBoard b, boolean isRed) {
        super(x, y, isRed, b, PieceClass.ADVISOR);
        minX = 3;
        maxX = 5;
        if (isRed) {
            minY = 0;
            maxY = 2;
        } else {
            minY = 7;
            maxY = 9;
        }
    }

    // REQUIRES: given coordinate (x, y) are valid coordinates on the board
    // EFFECTS: produce true if this can move to the given coordinate
    @Override
    public boolean canMoveTo(int x, int y) {
        if (x >= minX && x <= maxX && y <= maxY && y >= minY) {
            return Math.abs(getPosY() - y) == 1 && Math.abs(getPosX() - x) == 1;
        }
        return false;
    }
}
