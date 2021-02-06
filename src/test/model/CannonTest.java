package model;

import model.components.GameBoard;
import model.pieces.Cannon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CannonTest {
    Cannon c;
    GameBoard g;

    @BeforeEach
    public void setup() {
        g = new GameBoard();
        c = new Cannon(1, 2, g, true);
    }

    @Test
    public void constructorTest() {
        assertTrue(c.isRed());
        assertEquals(1, c.getPosX());
        assertEquals(2, c.getPosY());
        assertEquals(c, g.getPAt(1, 2));
    }

    @Test
    public void testCanMoveToEmptyBoard() {
        assertTrue(c.canMoveTo(1, 4));
        assertTrue(c.canMoveTo(0, 2));
        assertTrue(c.canMoveTo(7, 2));
        assertTrue(c.canMoveTo(1, 6));

        assertFalse(c.canMoveTo(2, 3));
        assertFalse(c.canMoveTo(0, 1));
        assertFalse(c.canMoveTo(2, 1));
        assertFalse(c.canMoveTo(0, 3));
    }

    @Test
    public void testCanMoveToHBlock() {
        addPiece(5, 2);
        assertTrue(c.canMoveTo(4, 2));
        assertTrue(c.canMoveTo(0, 2));
        assertTrue(c.canMoveTo(5, 2));

        assertFalse(c.canMoveTo(6, 2));
        assertFalse(c.canMoveTo(7, 2));
    }

    @Test
    public void testCanMoveToVBlock() {
        addPiece(1, 4);
        assertTrue(c.canMoveTo(1, 3));
        assertTrue(c.canMoveTo(1, 1));
        assertTrue(c.canMoveTo(1, 4));

        assertFalse(c.canMoveTo(1, 5));
        assertFalse(c.canMoveTo(1, 6));
    }

    @Test
    public void testCanCaptureNoInBetween() {
        assertFalse(c.canCapture(1, 4));
        assertFalse(c.canCapture(1, 2));
        assertFalse(c.canCapture(5, 2));
    }

    @Test
    public void testCanCapture1OnBoardV() {
        addPiece(5, 2);
        assertTrue(c.canCapture(6, 2));
        assertTrue(c.canCapture(7, 2));
        assertFalse(c.canCapture(5, 2));
        assertFalse(c.canCapture(4, 2));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersV() {
        addPiece(5, 2);
        addPiece(7, 2);
        assertTrue(c.canCapture(6, 2));
        assertTrue(c.canCapture(7, 2));
        assertFalse(c.canCapture(5, 2));
        assertFalse(c.canCapture(8, 2));
        assertFalse(c.canCapture(4, 2));

        addPiece(6, 2);
        assertTrue(c.canCapture(6, 2));
        assertFalse(c.canCapture(5, 2));
        assertFalse(c.canCapture(7, 2));
    }

    @Test
    public void testCanCapture1OnBoardH() {
        addPiece(1, 4);
        assertTrue(c.canCapture(1, 5));
        assertTrue(c.canCapture(1, 7));
        assertFalse(c.canCapture(1, 4));
        assertFalse(c.canCapture(1, 3));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersH() {
        addPiece(1, 4);
        addPiece(1, 6);
        assertTrue(c.canCapture(1, 5));
        assertTrue(c.canCapture(1, 6));
        assertFalse(c.canCapture(1, 7));
        assertFalse(c.canCapture(1, 8));
        assertFalse(c.canCapture(1, 4));

        addPiece(1, 5);
        assertTrue(c.canCapture(1, 5));
        assertFalse(c.canCapture(1, 6));
        assertFalse(c.canCapture(1, 4));
    }


    private void addPiece(int x, int y) {
        Cannon c2 = new Cannon(x, y, g, false);
    }

}
