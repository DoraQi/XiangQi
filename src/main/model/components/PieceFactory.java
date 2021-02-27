package model.components;

import exception.IllegalInputException;
import exception.OutOfBoundPositionException;
import model.pieces.*;

public class PieceFactory {
    // REQUIRES: given (x, y) position is empty on this board
    // MODIFIES: this
    // EFFECTS: place a piece of class pc and red if isRed, black if not, onto (x, y) of this board
    public static Piece makeNewPiece(String pc, int x, int y, GameBoard board, boolean isRed)
            throws IllegalInputException {
        switch (pc) {
            case "soldier":
                return new Soldier(x, y, board, isRed);
            case "general":
                return new General(x, y, board, isRed);
            case "cannon":
                return new Cannon(x, y, board, isRed);
            case "chariot":
                return new Chariot(x, y, board, isRed);
            case "advisor":
                return new Advisor(x, y, board, isRed);
            case "horse":
                return new Horse(x, y, board, isRed);
            case "elephant":
                return new Elephant(x, y, board, isRed);
            default:
                throw new IllegalInputException();
        }
    }
}
