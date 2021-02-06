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
        b = new GameBoard();
        g = new General(4, 1, b, true);
    }

    @Test
    public void testConstructor() {
        assertTrue(g.isRed());
        assertEquals(4, g.getPosX());
        assertEquals(1, g.getPosY());
        assertEquals(g, b.getPAt(4, 1));
    }

    @Test
    public void testCanMoveToInPalaceOneGrid() {
        assertTrue(g.canMoveTo(4, 2));
        assertTrue(g.canMoveTo(4, 0));
        assertTrue(g.canMoveTo(3, 1));
        assertTrue(g.canMoveTo(5, 1));
        assertFalse(g.canMoveTo(3, 0));
        assertFalse(g.canMoveTo(3, 2));
    }

    @Test
    public void testCanMoveToInPalaceMultipleGrids() {
        b.movePiece(g, 3, 0);
        assertTrue(g.canMoveTo(4, 0));
        assertFalse(g.canMoveTo(5, 0));
        assertFalse(g.canMoveTo(3, 2));
        assertFalse(g.canMoveTo(5, 2));

        b.movePiece(g, 5, 2);
        assertFalse(g.canMoveTo(3, 2));
        assertFalse(g.canMoveTo(5, 0));
        assertFalse(g.canMoveTo(3, 0));
    }

    @Test
    public void testCanMoveToOutOfPalace() {
        b.movePiece(g, 3, 2);
        assertFalse(g.canMoveTo(2, 2));
        assertFalse(g.canMoveTo(2, 3));

        b.movePiece(g, 5, 2);
        assertFalse(g.canMoveTo(5, 3));
        assertFalse(g.canMoveTo(6, 2));

        General g2 = new General(3, 7, b, false);
        assertFalse(g2.canMoveTo(2, 7));
        assertFalse(g2.canMoveTo(3, 6));
        b.movePiece(g2, 5, 7);
        assertFalse(g2.canMoveTo(6, 7));
        assertFalse(g2.canMoveTo(5, 6));
    }

    @Test
    public void testCanCaptureInPalace() {
        assertTrue(g.canCapture(4, 2));
        assertTrue(g.canCapture(4, 0));
        assertTrue(g.canCapture(3, 1));
        assertTrue(g.canCapture(5, 1));
        assertFalse(g.canCapture(3, 0));
        assertFalse(g.canCapture(3, 2));
    }

    @Test
    public void testCanCaptureOppGeneral() {
        General g2 = new General(4, 9, b, false);
        assertTrue(g.canCapture(4, 9));

        b.placePiece(new Advisor(4, 8, b, false));
        assertFalse(g.canCapture(4, 9));
        assertFalse(g.canCapture(3, 8));
    }
}
