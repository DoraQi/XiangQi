package model.pieces;

import model.GameBoard;

/**
 * Represents a Soldier piece of a game of XiangQi
 */
public class Soldier extends Piece {
    private boolean crossedRiver;

    public Soldier(int x, int y, boolean isRed) {
        super(x, y, isRed, "Soldier");
        crossedRiver = false;
    }

    // REQUIRES: to is a valid position on the board
    // EFFECTS: produce true if this pawn can move to given position:
    //           if not crossed river: advance by one only
    //           crossed river: advance by one, or one to the left, or one to the right
    @Override
    public boolean canMoveTo(int x, int y, GameBoard b) {
        return canMoveTo(x, y);
    }

    public boolean canMoveTo(int x, int y) {
        if (isRed()) {
            if (crossedRiver) {
                return ((x == getPosX() - 1 || x == getPosX() + 1) && y == getPosY())
                        || (x == getPosX() && y == getPosY() + 1);
            } else {
                return x == getPosX() && y == getPosY() + 1;
            }
        } else {
            if (crossedRiver) {
                return (y == getPosY() && (x == getPosX() - 1 || x == getPosX() + 1))
                        || (x == getPosX() && y == getPosY() - 1);
            } else {
                return x == getPosX() && y == getPosY() - 1;
            }
        }
    }


    // REQUIRES: to is a valid position on the board that this pawn can move to
    // EFFECTS: move to given position, check if crossed river
    public void move(int x, int y) {
        super.move(x, y);
        if (this.isRed() && y >= 6) {
            crossedRiver = true;
        } else if (!this.isRed() && y <= 5) {
            crossedRiver = true;
        }
    }

    public boolean crossedRiver() {
        return crossedRiver;
    }
}
