package model.pieces;

import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;

/**
 * Represents an Elephant piece of a game of XiangQi
 */
public class Elephant extends Piece {
    private final int maxY;
    private final int minY;

    // EFFECTS: instantiates an instance of Elephant
    //          throws OutOfBoundPositionException if position is on the other side of the river
    public Elephant(int x, int y, GameBoard b, boolean isRed)
            throws OutOfBoundPositionException, LocationOccupiedException {
        super(x, y, isRed, b, PieceClass.ELEPHANT);
        if (isRed) {
            maxY = 4;
            minY = 0;
        } else {
            maxY = 9;
            minY = 5;
        }
        if (y > maxY || y < minY) {
            throw new OutOfBoundPositionException();
        }
    }

    // REQUIRES: (x, y) to is a valid position on the board and is empty
    // EFFECTS: returns true if the given coordinate is orthogonal to the current position and that there are no
    //          other pieces in between
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
            return board.isEmptyAt(legSpotX, legSpotY);
        } else {
            return false;
        }
    }
}
