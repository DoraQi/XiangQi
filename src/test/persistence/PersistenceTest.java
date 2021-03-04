package persistence;

import exception.IllegalInputException;
import model.components.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static model.components.PieceFactory.makeNewPiece;
import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {
    JsonReader jReader;
    JsonWriter jWriter;
    GameBoard board;

    @BeforeEach
    public void setup() {
        jReader = new JsonReader();
        jWriter = new JsonWriter();
        board = new GameBoard();
    }

    @Test
    public void testSaveGameEmptyBoard() throws IllegalInputException, IOException {
        jWriter.saveGame(board, true);
        GameBoard board2 = jReader.loadGame();
        assertTrue(board.equals(board2));
    }

    @Test
    public void testSaveGameNonEmptyBoard() throws IllegalInputException, IOException {
        makeNewPiece("general", 4, 0, board, true);
        makeNewPiece("advisor", 4, 1, board, true);
        makeNewPiece("general", 4, 9, board, false);
        jWriter.saveGame(board, false);
        GameBoard b2 = jReader.loadGame();
        assertFalse(jReader.getFirstStart());
        assertTrue(board.equals(b2));
    }

    @Test
    public void testSaveGameNonEmptyBoardWithCapturedPieces() throws IllegalInputException, IOException {
        makeNewPiece("general", 4, 0, board, true);
        makeNewPiece("advisor", 4, 1, board, true);
        makeNewPiece("general", 4, 9, board, false);
        board.addCapturedPiece("soldier", 3, 5, false);
        board.addCapturedPiece("horse", 3, 3, true);
        jWriter.saveGame(board, false);
        GameBoard b2 = jReader.loadGame();
        assertFalse(jReader.getFirstStart());
        assertTrue(board.equals(b2));
    }

}
