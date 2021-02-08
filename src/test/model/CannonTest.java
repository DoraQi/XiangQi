package model;

import model.components.GameBoard;
import model.pieces.Cannon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CannonTest extends PieceTest{
    @BeforeEach
    public void setup() {
        board = new GameBoard();
        redP = new Cannon(1, 2, board, true);
        blackP = new Cannon(4, 7, board, false);
    }

    @Test
    public void constructorTest() {
        assertTrue(redP.isRed());
        assertEquals(1, redP.getPosX());
        assertEquals(2, redP.getPosY());
        assertEquals(redP, board.getPAt(1, 2));
    }

    @Test
    public void testCanMoveToEmptyBoard() {
        assertTrue(redP.canMoveTo(1, 4));
        assertTrue(redP.canMoveTo(0, 2));
        assertTrue(redP.canMoveTo(7, 2));
        assertTrue(redP.canMoveTo(1, 6));

        assertFalse(redP.canMoveTo(2, 3));
        assertFalse(redP.canMoveTo(0, 1));
        assertFalse(redP.canMoveTo(2, 1));
        assertFalse(redP.canMoveTo(0, 3));
    }

    @Test
    public void testCanMoveToRBlock() {
        addPiece(5, 2);
        assertTrue(redP.canMoveTo(4, 2));
        assertTrue(redP.canMoveTo(0, 2));
        assertTrue(redP.canMoveTo(5, 2));

        assertFalse(redP.canMoveTo(6, 2));
        assertFalse(redP.canMoveTo(7, 2));
    }

    @Test
    public void testCanMoveToVBlock() {
        addPiece(1, 4);
        assertTrue(redP.canMoveTo(1, 3));
        assertTrue(redP.canMoveTo(1, 1));
        assertTrue(redP.canMoveTo(1, 4));

        assertFalse(redP.canMoveTo(1, 5));
        assertFalse(redP.canMoveTo(1, 6));
    }

    @Test //?
    @Override
    public void testCanCapture() {}

    @Test
    public void testCanCaptureNoInBetween() {
        assertFalse(redP.canCapture(1, 4));
        assertFalse(redP.canCapture(1, 2));
        assertFalse(redP.canCapture(5, 2));
    }

    @Test
    public void testCanCapture1OnBoardV() {
        addPiece(5, 2);
        assertTrue(redP.canCapture(6, 2));
        assertTrue(redP.canCapture(7, 2));
        assertFalse(redP.canCapture(5, 2));
        assertFalse(redP.canCapture(4, 2));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersV() {
        addPiece(5, 2);
        addPiece(7, 2);
        assertTrue(redP.canCapture(6, 2));
        assertTrue(redP.canCapture(7, 2));
        assertFalse(redP.canCapture(5, 2));
        assertFalse(redP.canCapture(8, 2));
        assertFalse(redP.canCapture(4, 2));

        addPiece(6, 2);
        assertTrue(redP.canCapture(6, 2));
        assertFalse(redP.canCapture(5, 2));
        assertFalse(redP.canCapture(7, 2));
    }

    @Test
    public void testCanCapture1OnBoardH() {
        addPiece(1, 4);
        assertTrue(redP.canCapture(1, 5));
        assertTrue(redP.canCapture(1, 7));
        assertFalse(redP.canCapture(1, 4));
        assertFalse(redP.canCapture(1, 3));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersH() {
        addPiece(1, 4);
        addPiece(1, 6);
        assertTrue(redP.canCapture(1, 5));
        assertTrue(redP.canCapture(1, 6));
        assertFalse(redP.canCapture(1, 7));
        assertFalse(redP.canCapture(1, 8));
        assertFalse(redP.canCapture(1, 4));

        addPiece(1, 5);
        assertTrue(redP.canCapture(1, 5));
        assertFalse(redP.canCapture(1, 6));
        assertFalse(redP.canCapture(1, 4));
    }


    private void addPiece(int x, int y) {
        Cannon c2 = new Cannon(x, y, board, false);
    }
}
