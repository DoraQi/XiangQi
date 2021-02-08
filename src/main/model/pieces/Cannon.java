package model.pieces;

import model.components.GameBoard;

/**
 * Represents a Cannon piece of a game of XiangQi
 */
public class Cannon extends Piece {

    public Cannon(int x, int y, GameBoard b, boolean isRed) {
        super(x, y, isRed, b, "Cannon");
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

    // REQUIRES: given coordinates is a valid position on the board that is currently occupied by an opponent piece
    // EFFECTS: produce true iff this piece is aligned horizontally or vertically and
    //          there is 1 other piece between this piece and the given coordinate
    @Override
    public boolean canCapture(int x, int y) {
        // check if aligned horizontally or vertically
        // check if there exist one an only one non-empty square in between current position and target position
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();
        int countBetween = 0;
        if (deltaY != 0 && deltaX == 0) {
            int step = deltaY / Math.abs(deltaY);
            for (int i = getPosY() + step; i != y; i += step) {
                if (!board.isEmpty(x, i) && ++countBetween > 1) {
                    return false;
                }
            }
            return countBetween == 1;
        } else if (deltaX != 0 && deltaY == 0) {
            int step = deltaX / Math.abs(deltaX);
            for (int i = getPosX() + step; i != x; i += step) {
                if (!board.isEmpty(i, y) && ++countBetween > 1) {
                    return false;
                }
            }
            return countBetween == 1;
        }
        return false;
    }
}
