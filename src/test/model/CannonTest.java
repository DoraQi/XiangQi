package model;

import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import model.pieces.Cannon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CannonTest extends PieceTest{
    @BeforeEach
    public void setup() throws LocationOccupiedException, OutOfBoundPositionException {
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
        assertFalse(blackP.isRed());
        assertEquals(4, blackP.getPosX());
        assertEquals(7, blackP.getPosY());
        assertEquals(blackP, board.getPAt(4, 7));

    }

    @Test
    public void testCanMoveToEmptyBoard() {
        assertTrue(redP.canMoveTo(1, 4));
        assertTrue(redP.canMoveTo(0, 2));
        assertTrue(redP.canMoveTo(7, 2));
        assertTrue(redP.canMoveTo(1, 0));
        assertTrue(blackP.canMoveTo(1, 7));

        assertFalse(redP.canMoveTo(2, 3));
        assertFalse(redP.canMoveTo(0, 1));
        assertFalse(redP.canMoveTo(2, 1));
        assertFalse(redP.canMoveTo(0, 3));
        assertFalse(redP.canMoveTo(1, 2));
    }

    @Test
    public void testCanMoveToRBlock() throws LocationOccupiedException, OutOfBoundPositionException {
        addPiece(5, 2);
        assertTrue(redP.canMoveTo(4, 2));
        assertTrue(redP.canMoveTo(0, 2));
        assertTrue(redP.canMoveTo(5, 2));

        assertFalse(redP.canMoveTo(6, 2));
        assertFalse(redP.canMoveTo(7, 2));
    }

    @Test
    public void testCanMoveToVBlock() throws LocationOccupiedException, OutOfBoundPositionException {
        addPiece(1, 4);
        assertTrue(redP.canMoveTo(1, 3));
        assertTrue(redP.canMoveTo(1, 1));
        assertTrue(redP.canMoveTo(1, 4));

        assertFalse(redP.canMoveTo(1, 5));
        assertFalse(redP.canMoveTo(1, 6));
    }

    @Test
    @Override
    public void testCanCapture() {
        assertFalse(redP.canCapture(2, 4));
        assertFalse(redP.canCapture(0, 4));
        assertFalse(redP.canCapture(2, 1));
        assertFalse(redP.canCapture(0, 1));
    }

    @Test
    public void testCanCaptureNoInBetweenStraightline() {
        assertFalse(redP.canCapture(1, 4));
        assertFalse(redP.canCapture(1, 1));
        assertFalse(redP.canCapture(6, 2));
        assertFalse(redP.canCapture(0, 2));
        assertFalse(redP.canCapture(1, 2));
    }

    @Test
    public void testCanCapture1OnBoardH() throws LocationOccupiedException, OutOfBoundPositionException {
        addPiece(5, 2);
        assertTrue(redP.canCapture(6, 2));
        assertTrue(redP.canCapture(7, 2));
        assertFalse(redP.canCapture(5, 2));
        assertFalse(redP.canCapture(4, 2));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersH() throws LocationOccupiedException, OutOfBoundPositionException {
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
    public void testCanCapture1OnBoardV() throws LocationOccupiedException, OutOfBoundPositionException {
        addPiece(1, 4);
        assertTrue(redP.canCapture(1, 5));
        assertTrue(redP.canCapture(1, 7));
        assertFalse(redP.canCapture(1, 4));
        assertFalse(redP.canCapture(1, 3));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersV() throws LocationOccupiedException, OutOfBoundPositionException {
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


    private void addPiece(int x, int y) throws LocationOccupiedException, OutOfBoundPositionException {
        Cannon c2 = new Cannon(x, y, board, false);
    }
}
