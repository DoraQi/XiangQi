package persistence;

import model.components.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersistenceTest {
    JsonReader jreader;
    JsonWriter jWriter;
    GameBoard board;

    @BeforeEach
    public void setup() {
        jreader = new JsonReader();
        jWriter = new JsonWriter();
        board = new GameBoard();
    }

    @Test
    public void testSavingBoard() {
        
    }

}
