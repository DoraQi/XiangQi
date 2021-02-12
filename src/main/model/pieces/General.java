package model.pieces;

import model.components.GameBoard;

/**
 * Represents a General piece of a game of XiangQi
 */
public class General extends Piece {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    // REQUIRES: the given position is in the palace
    // EFFECTS: creates a General on (x, y) of b, and red if isRed, black if not
    public General(int x, int y, GameBoard b, boolean isRed) {
        super(x, y, isRed, b, "General");
        minX = 3;
        maxX = 5;
        if (isRed) {
            minY = 0;
            maxY = 2;
        } else {
            minY = 7;
            maxY = 9;
        }
    }

    // REQUIRES: given coordinate (x, y) is a valid position on b and is empty
    // EFFECTS: return true if given position is in the palace, 1 away, and orthogonal to current position;
    @Override
    public boolean canMoveTo(int x, int y) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY && (
                (Math.abs(x - getPosX()) == 1 && y == getPosY())
                        || (Math.abs(y - getPosY()) == 1 && x == getPosX()));
    }


    // REQUIRES: given coordinate (x, y) is a valid position on b
    // EFFECTS: return true if given position is in the palace, 1 away, and orthogonal to current position; or
    //          the opponent General is at given position, are aligned vertically, and there are no
    //          other pieces in between
    @Override
    public boolean canCapture(int x, int y) {
        if (canMoveTo(x, y)) {
            return true;
        } else if (x == getPosX() && board.getPAt(x, y).getPieceClass().equals("General")) {
            int deltaY = y - getPosY();
            int step = deltaY / Math.abs(deltaY);
            for (int i = getPosY() + step; i != y; i += step) {
                if (!board.isEmptyAt(x, i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
