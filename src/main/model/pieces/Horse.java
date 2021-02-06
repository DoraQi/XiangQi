package model.pieces;

import model.components.GameBoard;

/**
 * Represents a Horse piece of a game of XiangQi
 */
public class Horse extends Piece {

    public Horse(int x, int y, GameBoard b, boolean side) {
        super(x, y, side, b, "Horse");
    }

    // REQUIRES: given position (x, y) is an open position on the board
    // EFFECTS: return true if this horse can move to the given position:
    //          up 2 then left 1; up 2 then right 1; left 2 then up 1; or right 2 then up 1
    //          but if there is another piece in the way for the first move (the first part of the 2 square step), that
    //          step cannot be made
    @Override
    public boolean canMoveTo(int x, int y) {
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();

        if ((deltaY == -1 || deltaY == 1) && (deltaX == 2 || deltaX == -2)) {
            int legSpotX = getPosX() + deltaX / 2;
            return board.isEmpty(legSpotX, getPosY());
        } else if ((deltaX == -1 || deltaX == 1) && (deltaY == 2 || deltaY == -2)) {
            int legSpotY = getPosY() + deltaY / 2;
            return board.isEmpty(getPosX(), legSpotY);
        }

        return false;
    }
}
