package model;

import model.pieces.General;
import model.pieces.Piece;

/**
 * Represents a game of XiangQi with the board and players
 */
public class GameBoard {
    Player red;
    Player black;
    String gameLog;

    Piece[][] board; // Change to Map

    private static final int MAX_X_COORD = 8;
    private static final int MIN_X_COORD = 0;
    private static final int MAX_Y_COORD = 9;
    private static final int MIN_Y_COORD = 0;

    public GameBoard() {
        board = new Piece[][]{{null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}};
        gameLog = "";
    }

    // EFFECTS: return true if a King has been captured
    public boolean checkEndGame() {
        return false;
    }

    // EFFECTS: print out messages to end the game
    public void endGame() {
    }

    // MODIFIES: p, this
    // EFFECTS: prompt for player p to make the move, and make move
    public void play(Player p) {
    }

    // REQUIRES: pos is a valid position on the board
    // EFFECTS: checks if the given position (x, y) is occupied by any piece
    public boolean isEmpty(int x, int y) {
        return board[y][x] == null;
    }

    // REQUIRES: p is a piece owned by other
    // MODIFIES: playing, other
    // EFFECTS: playing captures p from other, removing p from the board
    public void capture(Piece p, Player playing, Player other) {
        removePiece(p.getPosX(), p.getPosY()); // remove p from board
        playing.capture(p);
        other.removePiece(p);
    }

    // REQUIRES: given piece can be moved to given coordinate (x, y)
    // MODIFIES: this, p
    // EFFECTS: move p to location (x, y)
    public void movePiece(Piece p, int x, int y) {
        // remove p from board
        removePiece(p.getPosX(), p.getPosY());
        // update position in p
        p.move(x, y);
        // place p onto (x, y)
        placePiece(p);
    }

    // EFFECTS: prints all model.pieces and their positions on the board
    public void printBoard() {
    }

    // MODIFIES: this
    // EFFECT: place given piece onto the correct position on board
    public void placePiece(Piece p) {
        board[p.getPosY()][p.getPosX()] = p;
    }

    // REQUIRES: given coordinate (x, y) is a valid position on board,
    //           given position is occupied by a Piece
    // EFFECTS: return the piece on the given position
    public Piece getPAt(int x, int y) {
        return board[y][x];
    }

    // MODIFIES: this
    // EFFECTS: sets up the board and players to play the game
    public void setUpGame(Player red, Player black) {
        makeSoldiers(red, black);
        makeChariots(red, black);
        makeCannons(red, black);
        makeElephants(red, black);
        makeHorses(red, black);
        makeGenerals(red, black);
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeGenerals(Player red, Player black) {
        General redG = new General(4, 0, true);
        General blackG = new General(4, 9, false);
        placePiece(redG);
        placePiece(blackG);
        red.addPiece(redG);
        black.addPiece(blackG);
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeHorses(Player red, Player black) {
        // create 4 horses
        // add each to board
        // add each to player
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeElephants(Player red, Player black) {
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeCannons(Player red, Player black) {
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeChariots(Player red, Player black) {
    }

    // MODIFIES: this
    // EFFECTS: makes all soldiers for both red and black sides and place on board
    private void makeSoldiers(Player red, Player black) {
    }

    // REQUIRES: position (x, y) is a valid position on the board
    // MODIFIES: this
    // EFFECTS: vacant the given spot
    private void removePiece(int x, int y) {
        board[y][x] = null;
    }

}
