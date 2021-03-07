package model.pieces;

import model.components.GameBoard;

/**
 * Represents a Chariot piece of a game of XiangQi
 */
public class Chariot extends Piece {

    // REQUIRES: given (x, y) is a valid empty position on board b
    // EFFECTS: creates a Chariot
    public Chariot(int x, int y, GameBoard b, boolean side) {
        super(x, y, side, b, PieceClass.CHARIOT);
    }

    // REQUIRES: given (x, y) is a valid empty position on board b
    // EFFECTS: produce true iff this piece is orthogonal to the given position and there are no other pieces
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
}
