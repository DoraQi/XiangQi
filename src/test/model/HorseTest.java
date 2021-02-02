package model;

import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    Horse h;
    GameBoard g;

    @BeforeEach
    public void setup() {
        h = new Horse(3, 4, true);
        g = new GameBoard();
        g.placePiece(h);

        assertTrue(h.isRed());
        assertEquals(3, h.getPosX());
        assertEquals(4, h.getPosY());
    }

    @Test
    public void testCanMoveToEmptyBoard() {
        assertTrue(h.canMoveTo(2, 6, g));
        assertTrue(h.canMoveTo(2,2, g));
        assertTrue(h.canMoveTo(4,6, g));
        assertTrue(h.canMoveTo(4,2, g));
        assertTrue(h.canMoveTo(1,5, g));
        assertTrue(h.canMoveTo(5,5, g));
        assertTrue(h.canMoveTo(1,3, g));
        assertTrue(h.canMoveTo(5,3, g));

        assertFalse(h.canMoveTo(3, 4, g));
        assertFalse(h.canMoveTo(3, 5, g));
        assertFalse(h.canMoveTo(3, 4, g));
        assertFalse(h.canMoveTo(3, 4, g));
        assertFalse(h.canMoveTo(3, 4, g));
    }

    @Test
    public void testCanMoveToBlockedHorizontal() {
        Horse h2 = new Horse(4, 4, true);
        g.placePiece(h2);

        assertTrue(h.canMoveTo(2, 6, g));
        assertTrue(h.canMoveTo(2,2, g));
        assertTrue(h.canMoveTo(4,6, g));
        assertTrue(h.canMoveTo(4,2, g));
        assertTrue(h.canMoveTo(1,5, g));
        assertTrue(h.canMoveTo(1,3, g));

        assertFalse(h.canMoveTo(5,3, g));
        assertFalse(h.canMoveTo(5,5, g));
    }

    @Test
    public void testCanMoveToBlockedVertical() {
        Horse h2 = new Horse(3, 3, true);
        g.placePiece(h2);

        assertTrue(h.canMoveTo(2, 6, g));
        assertTrue(h.canMoveTo(4,6, g));
        assertTrue(h.canMoveTo(1,5, g));
        assertTrue(h.canMoveTo(5,5, g));
        assertTrue(h.canMoveTo(1,3, g));
        assertTrue(h.canMoveTo(5,3, g));

        assertFalse(h.canMoveTo(2,2, g));
        assertFalse(h.canMoveTo(4,2, g));
    }

}
