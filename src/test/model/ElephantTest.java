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
        b = new GameBoard();
    }

    @Test
    public void testConstructor() {
        redE = new Elephant(4, 2, b, true);
        blackE = new Elephant(4, 7, b, false);
        assertTrue(redE.isRed());
        assertFalse(blackE.isRed());
        assertEquals(4, redE.getPosX());
        assertEquals(2, redE.getPosY());
        assertEquals(4, blackE.getPosX());
        assertEquals(7, blackE.getPosY());
        assertEquals(redE, b.getPAt(4, 2));
        assertEquals(blackE, b.getPAt(4, 7));
    }

    @Test
    public void testCanMoveToRedEBfRiverNotBlocked() {
        redE = new Elephant(4, 2, b, true);
        assertTrue(redE.canMoveTo(2, 4));
        assertTrue(redE.canMoveTo(2, 0));
        assertTrue(redE.canMoveTo(6, 4));
        assertTrue(redE.canMoveTo(6, 0));

        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 4; y++) {
                if ( (x == 2 && y == 4) || (x == 2 && y == 0)
                || (x == 6 && y == 4) || (x == 6 && y == 0)) {
                    continue;
                }
                assertFalse(redE.canMoveTo(x, y));
            }
        }
    }

    @Test
    public void testCanMoveToBlackBfRiverNotBlocked() {
        blackE = new Elephant(4, 7, b, false);
        assertTrue(blackE.canMoveTo(2, 9));
        assertTrue(blackE.canMoveTo(2, 5));
        assertTrue(blackE.canMoveTo(6, 9));
        assertTrue(blackE.canMoveTo(6, 5));

        for (int x = 0; x <= 8; x++) {
            for (int y = 9; y >= 5; y--) {
                if ( (x == 2 && y == 9) || (x == 2 && y == 5)
                        || (x == 6 && y == 9) || (x == 6 && y == 5)) {
                    continue;
                }
                assertFalse(blackE.canMoveTo(x, y));
            }
        }
    }

    @Test
    public void testCanMoveToCrossRiverNotBlocked() {
        redE = new Elephant(4, 2, b, true);
        blackE = new Elephant(4, 7, b, false);
        b.movePiece(redE, 3, 4);
        b.movePiece(blackE, 6, 5);
        assertFalse(redE.canMoveTo(1, 6));
        assertFalse(redE.canMoveTo(5, 6));
        assertFalse(blackE.canMoveTo(4, 3));
        assertFalse(blackE.canMoveTo(8, 3));
    }

    @Test
    public void testCanMoveToBlockedRed()
    {
        redE = new Elephant(4, 2, b, true);
        new Horse(3, 3, b, false);
        assertFalse(redE.canMoveTo(2, 4));

        new Horse(5, 3, b, false);
        assertFalse(redE.canMoveTo(6, 4));

        new Horse(3, 1, b, false);
        assertFalse(redE.canMoveTo(2, 0));

        new Horse(5, 1, b, true);
        assertFalse(redE.canMoveTo(6, 0));
    }

    @Test
    public void testCanMoveToBlockedBlack()
    {
        blackE = new Elephant(4, 2, b, false);
        new Horse(3, 3, b, false);
        assertFalse(blackE.canMoveTo(2, 4));

        new Horse(5, 3, b, false);
        assertFalse(blackE.canMoveTo(6, 4));

        new Horse(3, 1, b, false);
        assertFalse(blackE.canMoveTo(2, 0));

        new Horse(5, 1, b, true);
        assertFalse(blackE.canMoveTo(6, 0));
    }

}
