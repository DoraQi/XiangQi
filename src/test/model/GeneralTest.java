package model;

import model.components.GameBoard;
import model.pieces.Advisor;
import model.pieces.General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralTest {
    General g;
    GameBoard b;

    @BeforeEach
    public void setup() {
        g = new General(4, 1, true);
        b = new GameBoard();
        b.placePiece(g);

        assertTrue(g.isRed());
        assertEquals(4, g.getPosX());
        assertEquals(1, g.getPosY());
        assertEquals(g, b.getPAt(4, 1));
    }

    @Test
    public void testCanMoveToInPalaceOneGrid() {
        assertTrue(g.canMoveTo(4, 2, b));
        assertTrue(g.canMoveTo(4, 0, b));
        assertTrue(g.canMoveTo(3, 1, b));
        assertTrue(g.canMoveTo(5, 1, b));
        assertFalse(g.canMoveTo(3, 0, b));
        assertFalse(g.canMoveTo(3, 2, b));
    }

    @Test
    public void testCanMoveToInPalaceMultipleGrids() {
        b.movePiece(g, 3, 0);
        assertTrue(g.canMoveTo(4, 0, b));
        assertFalse(g.canMoveTo(5, 0, b));
        assertFalse(g.canMoveTo(3, 2, b));
        assertFalse(g.canMoveTo(5, 2, b));

        b.movePiece(g, 5, 2);
        assertFalse(g.canMoveTo(3, 2, b));
        assertFalse(g.canMoveTo(5, 0, b));
        assertFalse(g.canMoveTo(3, 0, b));
    }

    @Test
    public void testCanMoveToOutOfPalace() {
        b.movePiece(g, 3, 2);
        assertFalse(g.canMoveTo(2, 2, b));
        assertFalse(g.canMoveTo(2, 3, b));

        b.movePiece(g, 5, 2);
        assertFalse(g.canMoveTo(5, 3, b));
        assertFalse(g.canMoveTo(6, 2, b));

        General g2 = new General(3, 7, false);
        b.placePiece(g2);
        assertFalse(g2.canMoveTo(2, 7, b));
        assertFalse(g2.canMoveTo(3, 6, b));
        b.movePiece(g2, 5, 7);
        assertFalse(g2.canMoveTo(6, 7, b));
        assertFalse(g2.canMoveTo(5, 6, b));
    }

    @Test
    public void testCanCaptureInPalace() {
        assertTrue(g.canCapture(4, 2, b));
        assertTrue(g.canCapture(4, 0, b));
        assertTrue(g.canCapture(3, 1, b));
        assertTrue(g.canCapture(5, 1, b));
        assertFalse(g.canCapture(3, 0, b));
        assertFalse(g.canCapture(3, 2, b));
    }

    @Test
    public void testCanCaptureOppGeneral() {
        General g2 = new General(4, 9, false);
        b.placePiece(g2);
        assertTrue(g.canCapture(4, 9, b));

        b.placePiece(new Advisor(4, 8, false));
        assertFalse(g.canCapture(4, 9, b));
        assertFalse(g.canCapture(3, 8, b));
    }
}
