package model;

import model.components.GameBoard;
import model.pieces.Elephant;
import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElephantTest extends PieceTest{
    @BeforeEach
    public void setUp() {
        board = new GameBoard();
        redP = new Elephant(4, 2, board, true);
        blackP = new Elephant(4, 7, board, false);
    }

    @Test
    public void testConstructor() {
        assertTrue(redP.isRed());
        assertFalse(blackP.isRed());
        assertEquals(4, redP.getPosX());
        assertEquals(2, redP.getPosY());
        assertEquals(4, blackP.getPosX());
        assertEquals(7, blackP.getPosY());
        assertEquals(redP, board.getPAt(4, 2));
        assertEquals(blackP, board.getPAt(4, 7));
    }

    @Test
    public void testCanMoveToRedEBfRiverNotBlocked() {
        redP = new Elephant(4, 2, board, true);
        assertTrue(redP.canMoveTo(2, 4));
        assertTrue(redP.canMoveTo(2, 0));
        assertTrue(redP.canMoveTo(6, 4));
        assertTrue(redP.canMoveTo(6, 0));

        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 4; y++) {
                if ( (x == 2 && y == 4) || (x == 2 && y == 0)
                || (x == 6 && y == 4) || (x == 6 && y == 0)) {
                    continue;
                }
                assertFalse(redP.canMoveTo(x, y));
            }
        }
    }

    @Test
    public void testCanMoveToBlackBfRiverNotBlocked() {
        blackP = new Elephant(4, 7, board, false);
        assertTrue(blackP.canMoveTo(2, 9));
        assertTrue(blackP.canMoveTo(2, 5));
        assertTrue(blackP.canMoveTo(6, 9));
        assertTrue(blackP.canMoveTo(6, 5));

        for (int x = 0; x <= 8; x++) {
            for (int y = 9; y >= 5; y--) {
                if ( (x == 2 && y == 9) || (x == 2 && y == 5)
                        || (x == 6 && y == 9) || (x == 6 && y == 5)) {
                    continue;
                }
                assertFalse(blackP.canMoveTo(x, y));
            }
        }
    }

    @Test
    public void testCanMoveToCrossRiverNotBlocked() {
        redP = new Elephant(4, 2, board, true);
        blackP = new Elephant(4, 7, board, false);
        board.movePiece(redP, 3, 4);
        board.movePiece(blackP, 6, 5);
        assertFalse(redP.canMoveTo(1, 6));
        assertFalse(redP.canMoveTo(5, 6));
        assertFalse(blackP.canMoveTo(4, 3));
        assertFalse(blackP.canMoveTo(8, 3));
    }

    @Test
    public void testCanMoveToBlockedRed()
    {
        redP = new Elephant(4, 2, board, true);
        new Horse(3, 3, board, false);
        assertFalse(redP.canMoveTo(2, 4));

        new Horse(5, 3, board, false);
        assertFalse(redP.canMoveTo(6, 4));

        new Horse(3, 1, board, false);
        assertFalse(redP.canMoveTo(2, 0));

        new Horse(5, 1, board, true);
        assertFalse(redP.canMoveTo(6, 0));
    }

    @Test
    public void testCanMoveToBlockedBlack()
    {
        blackP = new Elephant(4, 2, board, false);
        new Horse(3, 3, board, false);
        assertFalse(blackP.canMoveTo(2, 4));

        new Horse(5, 3, board, false);
        assertFalse(blackP.canMoveTo(6, 4));

        new Horse(3, 1, board, false);
        assertFalse(blackP.canMoveTo(2, 0));

        new Horse(5, 1, board, true);
        assertFalse(blackP.canMoveTo(6, 0));
    }

}
