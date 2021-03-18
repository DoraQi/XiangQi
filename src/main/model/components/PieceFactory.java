package model.components;

import exception.IllegalInputException;
import model.pieces.*;

/**
 * Represents a factory for adding specified pieces to the given board
 */
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

    // REQUIRES: input string is in all lower case
    // MODIFIES: board
    // EFFECTS: add a piece according to the given instruction and returned added piece
    //          throws IllegalInputException if given coordinate is not a valid position on the board or if the given
    //          specification for the piece is illegal
    public static Piece createPiece(String inpt, GameBoard board) throws IllegalInputException {
        int x;
        int y;
        String pieceClass;
        boolean isRed;
        try {
            String[] inptSplit = inpt.split(" \\[|,|\\]");
            pieceClass = inptSplit[0];
            x = Integer.parseInt(inptSplit[1]);
            y = Integer.parseInt(inptSplit[2]);
            isRed = inptSplit[3].equals("r");
        } catch (RuntimeException e) {
            throw new IllegalInputException();
        }
        if (x > GameBoard.MAX_X_COORD || x < GameBoard.MIN_X_COORD
                || y > GameBoard.MAX_Y_COORD || y < GameBoard.MIN_Y_COORD) {
            throw new IllegalInputException();
        }
        return makeNewPiece(pieceClass, x, y, board, isRed);
    }

    // MODIFIES: board
    // EFFECTS: sets up the board and players to play the game
    //          throw any IllegalInputException thrown, which can never happen
    public static void setUpClassicGame(GameBoard board) {
        try {
            makeGenerals(board);
            makeAdvisors(board);
            makeCannons(board);
            makeChariots(board);
            makeHorses(board);
            makeElephants(board);
            makeSoldiers(board);
        } catch (IllegalInputException e) {
            throw new RuntimeException("Cannot happen");
        }
    }

    // MODIFIES: board
    // EFFECTS: makes all generals for both red and black sides
    private static void makeGenerals(GameBoard board) throws IllegalInputException {
        makeNewPiece("general", 4, 9, board, false);
        makeNewPiece("general", 4, 0, board, true);
    }

    // MODIFIES: board
    // EFFECTS: makes all advisors for both red and black sides
    //          never actually throws the exception...
    private static void makeAdvisors(GameBoard board) throws IllegalInputException {
        makeNewPiece("advisor", 3, 9, board, false);
        makeNewPiece("advisor", 5, 9, board, false);
        makeNewPiece("advisor", 3, 0, board, true);
        makeNewPiece("advisor", 5, 0, board, true);
    }


    // MODIFIES: board
    // EFFECTS: makes all horses for both red and black sides
    private static void makeHorses(GameBoard board) throws IllegalInputException {
        makeNewPiece("horse", 1, 9, board, false);
        makeNewPiece("horse", 7, 9, board, false);
        makeNewPiece("horse", 1, 0, board, true);
        makeNewPiece("horse", 7, 0, board, true);
    }

    // MODIFIES: board
    // EFFECTS: makes all elephants for both red and black sides
    private static void makeElephants(GameBoard board) throws IllegalInputException {
        makeNewPiece("elephant", 2, 9, board, false);
        makeNewPiece("elephant", 6, 9, board, false);
        makeNewPiece("elephant", 2, 0, board, true);
        makeNewPiece("elephant", 6, 0, board, true);
    }

    // MODIFIES: board
    // EFFECTS: makes all cannons for both red and black sides
    private static void makeCannons(GameBoard board) throws IllegalInputException {
        makeNewPiece("cannon", 1, 7, board, false);
        makeNewPiece("cannon",7, 7, board, false);
        makeNewPiece("cannon",1, 2, board, true);
        makeNewPiece("cannon",7, 2, board, true);
    }

    // MODIFIES: board
    // EFFECTS: makes all soldiers for both red and black sides
    private static void makeChariots(GameBoard board) throws IllegalInputException {
        makeNewPiece("chariot", 0, 9, board, false);
        makeNewPiece("chariot", 8, 9, board, false);
        makeNewPiece("chariot", 0, 0, board, true);
        makeNewPiece("chariot", 8, 0, board, true);
    }

    // MODIFIES: board
    // EFFECTS: makes all soldiers for both red and black sides
    private static void makeSoldiers(GameBoard board) throws IllegalInputException {
        for (int i = 0; i <= 8; i += 2) {
            makeNewPiece("soldier", i, 3, board, true);
            makeNewPiece("soldier", i, 6, board, false);
        }
    }
}
