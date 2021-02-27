package model.pieces;

import exception.OutOfBoundPositionException;
import model.components.GameBoard;

/**
 * Represents an Advisor piece of a game of XiangQi
 */
public class Advisor extends Piece {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    // EFFECTS: instantiate an Advisor with given positions and side
    //          throws OutOfBoundPositionException if position is outside of designated palace
    public Advisor(int x, int y, GameBoard b, boolean isRed) throws OutOfBoundPositionException {
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
        if (x > maxX || x < minX || y > maxY || y < minY) {
            throw new OutOfBoundPositionException();
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
