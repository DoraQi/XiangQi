package model.pieces;

import model.components.GameBoard;

/**
 * Represents a Chariot piece of a game of XiangQi
 */
public class Chariot extends Piece {

    public Chariot(int x, int y, GameBoard b, boolean side) {
        super(x, y, side, b, "Chariot");
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();
        // check if aligned horizontally or vertically
        // check if each square along the way are vacant
        if (deltaY != 0 && deltaX == 0) {
            int step = deltaY / Math.abs(deltaY);
            for (int i = getPosY() + step; i != y; i += step) {
                if (!board.isEmpty(x, i)) {
                    return false;
                }
            }
            return true;
        } else if (deltaX != 0 && deltaY == 0) {
            int step = deltaX / Math.abs(deltaX);
            for (int i = getPosX() + step; i != x; i += step) {
                if (!board.isEmpty(i, y)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
