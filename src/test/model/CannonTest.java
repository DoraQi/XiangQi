package model;

import model.pieces.Cannon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CannonTest {
    Cannon c;
    GameBoard g;

    @BeforeEach
    public void setup() {
        c = new Cannon(1, 2, true);
        g = new GameBoard();
        g.placePiece(c);

        assertTrue(c.isRed());
        assertEquals(1, c.getPosX());
        assertEquals(2, c.getPosY());
    }

    @Test
    public void testCanMoveToEmptyBoard() {
        assertTrue(c.canMoveTo(1, 4, g));
        assertTrue(c.canMoveTo(0, 2, g));
        assertTrue(c.canMoveTo(7, 2, g));
        assertTrue(c.canMoveTo(1, 6, g));

        assertFalse(c.canMoveTo(2, 3, g));
        assertFalse(c.canMoveTo(0, 1, g));
        assertFalse(c.canMoveTo(2, 1, g));
        assertFalse(c.canMoveTo(0, 3, g));
    }

    @Test
    public void testCanMoveToHBlock() {
        addPiece(5, 2);
        assertTrue(c.canMoveTo(4, 2, g));
        assertTrue(c.canMoveTo(0, 2, g));
        assertTrue(c.canMoveTo(5, 2, g));

        assertFalse(c.canMoveTo(6, 2, g));
        assertFalse(c.canMoveTo(7, 2, g));
    }

    @Test
    public void testCanMoveToVBlock() {
        addPiece(1, 4);
        assertTrue(c.canMoveTo(1, 3, g));
        assertTrue(c.canMoveTo(1, 1, g));
        assertTrue(c.canMoveTo(1, 4, g));

        assertFalse(c.canMoveTo(1, 5, g));
        assertFalse(c.canMoveTo(1, 6, g));
    }

    @Test
    public void testCanCaptureNoInBetween() {
        assertFalse(c.canCapture(1, 4, g));
        assertFalse(c.canCapture(1, 2, g));
        assertFalse(c.canCapture(5, 2, g));
    }

    @Test
    public void testCanCapture1OnBoardV() {
        addPiece(5, 2);
        assertTrue(c.canCapture(6, 2, g));
        assertTrue(c.canCapture(7, 2, g));
        assertFalse(c.canCapture(5, 2, g));
        assertFalse(c.canCapture(4, 2, g));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersV() {
        addPiece(5, 2);
        addPiece(7, 2);
        assertTrue(c.canCapture(6, 2, g));
        assertTrue(c.canCapture(7, 2, g));
        assertFalse(c.canCapture(5, 2, g));
        assertFalse(c.canCapture(8, 2, g));
        assertFalse(c.canCapture(4, 2, g));

        addPiece(6, 2);
        assertTrue(c.canCapture(6, 2, g));
        assertFalse(c.canCapture(5, 2, g));
        assertFalse(c.canCapture(7, 2, g));
    }

    @Test
    public void testCanCapture1OnBoardH() {
        addPiece(1, 4);
        assertTrue(c.canCapture(1, 5, g));
        assertTrue(c.canCapture(1, 7, g));
        assertFalse(c.canCapture(1, 4, g));
        assertFalse(c.canCapture(1, 3, g));
    }

    @Test
    public void testCanCaptureMultipleInBetweenersH() {
        addPiece(1, 4);
        addPiece(1, 6);
        assertTrue(c.canCapture(1, 5, g));
        assertTrue(c.canCapture(1, 6, g));
        assertFalse(c.canCapture(1, 7, g));
        assertFalse(c.canCapture(1, 8, g));
        assertFalse(c.canCapture(1, 4, g));

        addPiece(1, 5);
        assertTrue(c.canCapture(1, 5, g));
        assertFalse(c.canCapture(1, 6, g));
        assertFalse(c.canCapture(1, 4, g));
    }


    private void addPiece(int x, int y) {
        Cannon c2 = new Cannon(x, y, false);
        g.placePiece(c2);
    }

}
