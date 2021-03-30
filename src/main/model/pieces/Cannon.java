package model.pieces;

import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;

/**
 * Represents a Cannon piece of a game of XiangQi
 */
public class Cannon extends Piece {

    // REQUIRES: given (x, y) is a valid empty position on board b
    // EFFECTS: instantiates an instance of Cannon
    public Cannon(int x, int y, GameBoard b, boolean isRed)
            throws LocationOccupiedException, OutOfBoundPositionException {
        super(x, y, isRed, b, PieceClass.CANNON);
    }

    // REQUIRES: given position (x,y) is a valid, empty position on board
    // EFFECTS: produce true iff this piece is orthogonal to the current position and there are no other pieces
    //          in between
    @Override
    public boolean canMoveTo(int x, int y) {
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();
        if (deltaY != 0 && deltaX == 0) {
            int step = deltaY / Math.abs(deltaY);
            for (int i = getPosY() + step; i != y; i += step) {
                if (!board.isEmptyAt(x, i)) {
                    return false;
                }
            }
            return true;
        } else if (deltaX != 0 && deltaY == 0) {
            int step = deltaX / Math.abs(deltaX);
            for (int i = getPosX() + step; i != x; i += step) {
                if (!board.isEmptyAt(i, y)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // REQUIRES: given position (x,y) is a valid position on board and occupied by an opponent's piece
    // EFFECTS: produce true iff this piece is orthogonal to the current position and there is exactly one piece in
    //          between
    @Override
    public boolean canCapture(int x, int y) {
        int deltaX = x - getPosX();
        int deltaY = y - getPosY();
        int countBetween = 0;
        if (deltaY != 0 && deltaX == 0) {
            int step = deltaY / Math.abs(deltaY);
            for (int i = getPosY() + step; i != y; i += step) {
                if (!board.isEmptyAt(x, i) && ++countBetween > 1) {
                    return false;
                }
            }
            return countBetween == 1;
        } else if (deltaX != 0 && deltaY == 0) {
            int step = deltaX / Math.abs(deltaX);
            for (int i = getPosX() + step; i != x; i += step) {
                if (!board.isEmptyAt(i, y) && ++countBetween > 1) {
                    return false;
                }
            }
            return countBetween == 1;
        }
        return false;
    }
}
