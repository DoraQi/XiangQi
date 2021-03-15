package model.components;

import model.pieces.Piece;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Player playing a game of XiangQi
 */
public class Player implements Writable {
    private final boolean isRed;
    private List<Piece> pieces;
    private List<Piece> captured;

    public Player(boolean isRed) {
        this.isRed = isRed;
        pieces = new ArrayList<>();
        captured = new ArrayList<>();
    }

    // REQUIRES: given piece is owned by the opponent
    // MODIFIES: this
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
    public List<Piece> getPieces() {
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

    // EFFECTS: clear all pieces and captured pieces from this player
    public void clear() {
        pieces.clear();
        captured.clear();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("isRed", isRed);
        json.put("captured", capturedToJson());
        return json;
    }

    // EFFECTS: return all captured pieces as a JSON array
    private JSONArray capturedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Piece p : captured) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return isRed == player.isRed
                && pieces.equals(player.pieces)
                && captured.equals(player.captured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRed, pieces, captured);
    }

    public List<Piece> getCapturedPieces() {
        return captured;
    }
}
