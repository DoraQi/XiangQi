package model.pieces;

import model.components.GameBoard;

/**
 * Represents a Soldier piece of a game of XiangQi
 */
public class Soldier extends Piece {
    private boolean crossedRiver;

    // REQUIRES: given (x, y) is a valid empty position on board b
    // EFFECTS: create an instance of a Soldier
    public Soldier(int x, int y, GameBoard b, boolean isRed) {
        super(x, y, isRed, b, "Soldier");
        updateCrossRiver();
    }

    // REQUIRES: to is a valid position on the board
    // EFFECTS: produce true if this pawn can move to given position:
    //           if not crossed river: advance by one only
    //           crossed river: advance by one, or one to the left, or one to the right
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
        updateCrossRiver();
    }

    private void updateCrossRiver() {
        if (this.isRed() && getPosY() >= 5) {
            crossedRiver = true;
        } else crossedRiver = !this.isRed() && getPosY() <= 4;
    }
}
