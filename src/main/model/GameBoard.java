package model;

import model.pieces.Piece;

public class GameBoard {
    Player red;
    Player black;
    String gameLog;

    private static final int MAX_X_COORD = 9;
    private static final int MIN_X_COORD = 1;
    private static final int MAX_Y_COORD = 10;
    private static final int MIN_Y_COORD = 1;

    public GameBoard(Player p1, Player p2) {
        this.red = p1;
        p1.setSide(true);
        this.black = p2;
        p2.setSide(false);
    }

    // EFFECTS: sets up the board and players to play the game
    public void startGame() {}

    // EFFECTS: return true if a King has been captured
    public boolean checkEndGame() {
        return false;
    }

    // EFFECTS: print out messages to end the game
    public void endGame() {}

    // MODIFIES: p, this
    // EFFECTS: prompt for player p to make the move, and make move
    public void play(Player p) {}

    // REQUIRES: pos is a valid position on the board
    // EFFECTS: checks if the given position is occupied by any piece
    public boolean checkOccupancy(String pos) { return false; }

    // REQUIRES: p is a piece owned by other
    // MODIFIES: playing, other
    // EFFECTS: playing captures p from other
    public void capture(Piece p, Player playing, Player other) {}

    // EFFECTS: prints all model.pieces and their positions on the board
    public void printBoard() {}

    // REQUIRES
    private int getYCoord(String position) {
        return 0;
    }

    private int getXCoord(String position) {
        return 0;
    }
}
