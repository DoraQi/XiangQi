package model;

import model.pieces.Piece;

import java.util.ArrayList;

/**
 * Represents a Player playing a game of XiangQi
 */
public class Player {
    private boolean isRed;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> captured;

    // REQUIRES: from and to are valid positions on the board
    // MODIFIES: this
    // EFFECTS: moves the piece on from to position specified by to
    public void makeMove(int fromX, int fromY, int toX, int toY) {}

    // REQUIRES: given piece is owned by the opponent
    // MODIFIES: this, target
    // EFFECTS: captures the given piece
    public void capture(Piece target) {
        captured.add(target);
    }

    // REQUIRES: given piece is on the same side as this player
    // EFFECTS: add the given piece to the possession of this player
    public void addPiece(Piece p) {
        pieces.add(p);
    }

    // REQUIRES: given piece is in possession of the player
    // EFFECTS: remove the given piece from the possession of this player
    public void removePiece(Piece p) {
        pieces.remove(p);
    }

    // EFFECTS: produce true if this player has p
    public boolean has(Piece p) {
        return false;
    }

    // EFFECTS: produce true if this player has captured p
    public boolean captured(Piece p) {
        return false;
    }

    public void setSide(boolean side) {
        this.isRed = side;
    }
}
