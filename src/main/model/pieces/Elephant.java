package model.pieces;

import model.components.GameBoard;

/**
 * Represents an Elephant piece of a game of XiangQi
 */
public class Elephant extends Piece {
    private final int maxY;
    private final int minY;

    public Elephant(int x, int y, GameBoard b, boolean isRed) {
        super(x, y, isRed, b, "Elephant");
        if (isRed) {
            maxY = 4;
            minY = 0;
        } else {
            maxY = 9;
            minY = 5;
        }
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (y > maxY || y < minY) {
            return false;
        }
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();
        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
            int legSpotX = (getPosX() + x) / 2;
            int legSpotY = (getPosY() + y) / 2;
            return board.isEmpty(legSpotX, legSpotY);
        } else {
            return false;
        }
    }
}
