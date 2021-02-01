package model.pieces;

public abstract class Piece {
    private int posX;
    private int posY;
    private boolean side;



    public Piece(int x, int y, boolean side) {
        move(x, y);
        this.side = side;
    }


    // REQUIRES: given coordinate is an empty square on the board
    // MODIFIES: this
    // EFFECTS: move this piece from current position to target position
    public void move(int x, int y) {
        posX = x;
        posY = y;
    }

    // REQUIRES: given coordinate is a valid position on the board
    // EFFECTS: return true if any piece on the given coordinate can be captured by this piece
    public boolean canCapture(int x, int y) {
        return canMoveTo(x, y);
    }

    // REQUIRES: to is a square on the board
    // EFFECTS: produce true if this piece can move to target location
    public abstract boolean canMoveTo(int x, int y);

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public boolean isRed() {
        return side;
    }
}
