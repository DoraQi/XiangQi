package model;

import model.components.GameBoard;
import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest extends PieceTest{
    @BeforeEach
    public void setup() {
        board = new GameBoard();
        redP = new Horse(3, 4, board,true);
        blackP = new Horse(7, 7, board, false);

        board.placePiece(redP);

        assertTrue(redP.isRed());
        assertEquals(3, redP.getPosX());
        assertEquals(4, redP.getPosY());
    }

    @Test
    public void testCanMoveTo() {
        assertTrue(redP.canMoveTo(2, 6));
        assertTrue(redP.canMoveTo(2,2));
        assertTrue(redP.canMoveTo(4,6));
        assertTrue(redP.canMoveTo(4,2));
        assertTrue(redP.canMoveTo(1,5));
        assertTrue(redP.canMoveTo(5,5));
        assertTrue(redP.canMoveTo(1,3));
        assertTrue(redP.canMoveTo(5,3));

        assertFalse(redP.canMoveTo(3, 4));
        assertFalse(redP.canMoveTo(3, 5));
        assertFalse(redP.canMoveTo(3, 4));
        assertFalse(redP.canMoveTo(3, 4));
        assertFalse(redP.canMoveTo(3, 4));
    }

    @Test
    public void testCanMoveToBlockedHorizontal() {
        board.movePiece(blackP, 4, 4);

        assertTrue(redP.canMoveTo(2, 6));
        assertTrue(redP.canMoveTo(2,2));
        assertTrue(redP.canMoveTo(4,6));
        assertTrue(redP.canMoveTo(4,2));
        assertTrue(redP.canMoveTo(1,5));
        assertTrue(redP.canMoveTo(1,3));

        assertFalse(redP.canMoveTo(5,3));
        assertFalse(redP.canMoveTo(5,5));
    }

    @Test
    public void testCanMoveToBlockedVertical() {
        board.movePiece(blackP, 3, 3);

        assertTrue(redP.canMoveTo(2, 6));
        assertTrue(redP.canMoveTo(4,6));
        assertTrue(redP.canMoveTo(1,5));
        assertTrue(redP.canMoveTo(5,5));
        assertTrue(redP.canMoveTo(1,3));
        assertTrue(redP.canMoveTo(5,3));

        assertFalse(redP.canMoveTo(2,2));
        assertFalse(redP.canMoveTo(4,2));
    }

}
