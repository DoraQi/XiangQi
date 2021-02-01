package model;

import model.pieces.Piece;

import java.util.ArrayList;

public class Player {
    private boolean isRed;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> captured;

    // REQUIRES: from and to are valid positions on the board
    // MODIFIES: this
    // EFFECTS: moves the piece on from to position specified by to
    public void makeMove(String from, String to) {}

    // REQUIRES: given piece is owned by the opponent
    // MODIFIES: this, target
    // EFFECTS: captures the given piece
    public void capture(Piece target) {}

    // EFFECTS: produce true if this player has p
    public boolean has(Piece p) { return false; }

    // EFFECTS: produce true if this player has captured p
    public boolean captured(Piece p) { return false; }

    public void setSide(boolean side) {
        this.isRed = side;
    }
}
