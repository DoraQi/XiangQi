package model.pieces;

public class Soldier extends Piece {
    private boolean crossedRiver;

    public Soldier(int x, int y, boolean isRed) {
        super(x, y, isRed);
        crossedRiver = false;
    }

    // REQUIRES: to is a valid position on the board
    // EFFECTS: produce true if this pawn can move to given position
    @Override
    public boolean canMoveTo(int x, int y) {
        if (isRed()) {
            if (crossedRiver) {
                return (x >= getPosX() - 1 && x <= getPosX() + 1 && y == getPosY())
                        || (x == getPosX() && (y == getPosY() + 1 || y == getPosY()));
            } else {
                return x == getPosX() && y <= getPosY() + 1 && y >= getPosY();
            }
        } else {
            if (crossedRiver) {
                return (y == getPosY() && x >= getPosX() - 1 && x <= getPosX() + 1)
                        || (x == getPosX() && (y == getPosY() - 1 || y == getPosY()));
            } else {
                return x == getPosX() && y >= getPosY() - 1 && y <= getPosY();
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
