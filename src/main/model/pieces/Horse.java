package model.pieces;

import model.GameBoard;

/**
 * Represents a Horse piece of a game of XiangQi
 */
public class Horse extends Piece {

    public Horse(int x, int y, boolean side) {
        super(x, y, side, "Horse");
    }

    // REQUIRES: given position (x, y) is an open position on the board
    // EFFECTS: return true if this horse can move to the given position
    @Override
    public boolean canMoveTo(int x, int y, GameBoard b) {
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();

        if ((deltaY == -1 || deltaY == 1) && (deltaX == 2 || deltaX == -2)) {
            int legSpotX = getPosX() + deltaX / 2;
            return b.isEmpty(legSpotX, getPosY());
        } else if ((deltaX == -1 || deltaX == 1) && (deltaY == 2 || deltaY == -2)) {
            int legSpotY = getPosY() + deltaY / 2;
            return b.isEmpty(getPosX(), legSpotY);
        }

        return false;
    }
}
