package model.components;

import model.pieces.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a Player playing a game of XiangQi
 */
public class Player {
    private final boolean isRed;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> captured;

    public Player(boolean isRed) {
        this.isRed = isRed;
        pieces = new ArrayList<>();
        captured = new ArrayList<>();
    }

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

    // EFFECTS: returns a list of all the pieces this player has
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    // EFFECTS: returns true if this player is on the red side, false otherwise
    public boolean isRed() {
        return isRed;
    }

    // EFFECTS: returns true if this player has p
    public boolean has(Piece p) {
        return pieces.contains(p);
    }

    // EFFECTS: returns true if this player has captured p
    public boolean hasCaptured(Piece p) {
        return captured.contains(p);
    }
}
