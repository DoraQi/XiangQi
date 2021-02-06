package model;

import model.components.GameBoard;
import model.pieces.Elephant;
import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElephantTest {
    Elephant redE;
    Elephant blackE;
    GameBoard b;

    @BeforeEach
    public void setUp() {
        redE = new Elephant(4, 2, true);
        blackE = new Elephant(4, 7, false);
        b = new GameBoard();
        b.placePiece(redE);
        b.placePiece(blackE);

        assertTrue(redE.isRed());
        assertFalse(blackE.isRed());
        assertEquals(4, redE.getPosX());
        assertEquals(2, redE.getPosY());
        assertEquals(4, blackE.getPosX());
        assertEquals(7, blackE.getPosY());
    }

    @Test
    public void testCanMoveToRedEBfRiverNotBlocked() {
        assertTrue(redE.canMoveTo(2, 4, b));
        assertTrue(redE.canMoveTo(2, 0, b));
        assertTrue(redE.canMoveTo(6, 4, b));
        assertTrue(redE.canMoveTo(6, 0, b));

        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 4; y++) {
                if ( (x == 2 && y == 4) || (x == 2 && y == 0)
                || (x == 6 && y == 4) || (x == 6 && y == 0)) {
                    continue;
                }
                assertFalse(redE.canMoveTo(x, y, b));
            }
        }
    }

    @Test
    public void testCanMoveToBlackBfRiverNotBlocked() {
        assertTrue(blackE.canMoveTo(2, 9, b));
        assertTrue(blackE.canMoveTo(2, 5, b));
        assertTrue(blackE.canMoveTo(6, 9, b));
        assertTrue(blackE.canMoveTo(6, 5, b));

        for (int x = 0; x <= 8; x++) {
            for (int y = 9; y >= 5; y--) {
                if ( (x == 2 && y == 9) || (x == 2 && y == 5)
                        || (x == 6 && y == 9) || (x == 6 && y == 5)) {
                    continue;
                }
                assertFalse(redE.canMoveTo(x, y, b));
            }
        }
    }

    @Test
    public void testCanMoveToCrossRiverNotBlocked() {
        b.movePiece(redE, 3, 4);
        b.movePiece(blackE, 6, 5);
        assertFalse(redE.canMoveTo(1, 6, b));
        assertFalse(redE.canMoveTo(5, 6, b));
        assertFalse(blackE.canMoveTo(4, 3, b));
        assertFalse(blackE.canMoveTo(8, 3, b));
    }

    @Test
    public void testCanMoveToBlocked()
    {
        b.placePiece(new Horse(3, 3, true));
        assertFalse(redE.canMoveTo(2, 4, b));

        b.placePiece(new Horse(5, 3, false));
        assertFalse(redE.canMoveTo(6, 4, b));

        b.placePiece(new Horse(3, 1, false));
        assertFalse(redE.canMoveTo(2, 0, b));

        b.placePiece(new Horse(5, 1, true));
        assertFalse(redE.canMoveTo(6, 0, b));
    }
}
