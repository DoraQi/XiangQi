package model;

import exception.IllegalInputException;
import model.components.GameBoard;
import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.components.PieceFactory.createPiece;
import static org.junit.jupiter.api.Assertions.*;

public class HorseTest extends PieceTest{
    @BeforeEach
    public void setup() {
        board = new GameBoard();
        redP = new Horse(3, 4, board,true);
        blackP = new Horse(7, 7, board, false);

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
    public void testCanMoveToBlockedHorizontalR() throws IllegalInputException {
        createPiece("soldier [4,4]b", board);

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
    public void testCanMoveToBlockedHorizontalL() throws IllegalInputException {
        createPiece("soldier [2,4]b", board);

        assertTrue(redP.canMoveTo(2, 6));
        assertTrue(redP.canMoveTo(2,2));
        assertTrue(redP.canMoveTo(4,6));
        assertTrue(redP.canMoveTo(4,2));
        assertTrue(redP.canMoveTo(5,3));
        assertTrue(redP.canMoveTo(5,5));

        assertFalse(redP.canMoveTo(1,5));
        assertFalse(redP.canMoveTo(1,3));
    }

    @Test
    public void testCanMoveToBlockedVerticalBelow() throws IllegalInputException {
        createPiece("soldier [3,3]b", board);

        assertTrue(redP.canMoveTo(2, 6));
        assertTrue(redP.canMoveTo(4,6));
        assertTrue(redP.canMoveTo(1,5));
        assertTrue(redP.canMoveTo(5,5));
        assertTrue(redP.canMoveTo(1,3));
        assertTrue(redP.canMoveTo(5,3));

        assertFalse(redP.canMoveTo(2,2));
        assertFalse(redP.canMoveTo(4,2));
    }

    @Test
    public void testCanMoveToBlockedVerticalAbove() throws IllegalInputException {
        createPiece("soldier [3,5]b", board);

        assertTrue(redP.canMoveTo(1,5));
        assertTrue(redP.canMoveTo(5,5));
        assertTrue(redP.canMoveTo(1,3));
        assertTrue(redP.canMoveTo(5,3));
        assertTrue(redP.canMoveTo(2,2));
        assertTrue(redP.canMoveTo(4,2));

        assertFalse(redP.canMoveTo(2, 6));
        assertFalse(redP.canMoveTo(4,6));
    }

}
