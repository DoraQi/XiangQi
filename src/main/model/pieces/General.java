package model.pieces;

import model.GameBoard;

/**
 * Represents a General piece of a game of XiangQi
 */
public class General extends Piece {
    private final int MIN_X;
    private final int MAX_X;
    private final int MIN_Y;
    private final int MAX_Y;

    public General(int x, int y, boolean isRed) {
        super(x, y, isRed, "General");
        MIN_X = 3;
        MAX_X = 5;
        if (isRed) {
            MIN_Y = 0;
            MAX_Y = 2;
        } else {
            MIN_Y = 7;
            MAX_Y = 9;
        }
    }

    // REQUIRES: given coordinate (x, y) is a valid position on b
    // EFFECTS: return true if given position is in the palace, 1 away, and orthogonal to current position;
    @Override
    public boolean canMoveTo(int x, int y, GameBoard b) {
        return x >= MIN_X && x <= MAX_X && y >= MIN_Y && y <= MAX_Y && (
                (Math.abs(x - getPosX()) == 1 && y == getPosY())
                        || (Math.abs(y - getPosY()) == 1 && x == getPosX()));
    }


    // REQUIRES: given coordinate (x, y) is a valid position on b
    // EFFECTS: return true if given position is in the palace, 1 away, and orthogonal to current position; or
    //          the opponent General is at given position, are aligned vertically, and there are no
    //          other pieces in between
    @Override
    public boolean canCapture(int x, int y, GameBoard b) {
        if (!canMoveTo(x, y, b)) {
            int deltaX = x - getPosX();
            int deltaY = y - getPosY();
            if (deltaY != 0 && deltaX == 0) {
                int step = deltaY / Math.abs(deltaY);
                for (int i = getPosY() + step; i != y; i += step) {
                    if (!b.isEmpty(x, i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
