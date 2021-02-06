package model;

import model.components.GameBoard;
import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    Horse h;
    GameBoard b;

    @BeforeEach
    public void setup() {
        b = new GameBoard();
        h = new Horse(3, 4, b,true);

        b.placePiece(h);

        assertTrue(h.isRed());
        assertEquals(3, h.getPosX());
        assertEquals(4, h.getPosY());
    }

    @Test
    public void testCanMoveToEmptyBoard() {
        assertTrue(h.canMoveTo(2, 6));
        assertTrue(h.canMoveTo(2,2));
        assertTrue(h.canMoveTo(4,6));
        assertTrue(h.canMoveTo(4,2));
        assertTrue(h.canMoveTo(1,5));
        assertTrue(h.canMoveTo(5,5));
        assertTrue(h.canMoveTo(1,3));
        assertTrue(h.canMoveTo(5,3));

        assertFalse(h.canMoveTo(3, 4));
        assertFalse(h.canMoveTo(3, 5));
        assertFalse(h.canMoveTo(3, 4));
        assertFalse(h.canMoveTo(3, 4));
        assertFalse(h.canMoveTo(3, 4));
    }

    @Test
    public void testCanMoveToBlockedHorizontal() {
        new Horse(4, 4, b,true);

        assertTrue(h.canMoveTo(2, 6));
        assertTrue(h.canMoveTo(2,2));
        assertTrue(h.canMoveTo(4,6));
        assertTrue(h.canMoveTo(4,2));
        assertTrue(h.canMoveTo(1,5));
        assertTrue(h.canMoveTo(1,3));

        assertFalse(h.canMoveTo(5,3));
        assertFalse(h.canMoveTo(5,5));
    }

    @Test
    public void testCanMoveToBlockedVertical() {
        new Horse(3, 3, b, true);

        assertTrue(h.canMoveTo(2, 6));
        assertTrue(h.canMoveTo(4,6));
        assertTrue(h.canMoveTo(1,5));
        assertTrue(h.canMoveTo(5,5));
        assertTrue(h.canMoveTo(1,3));
        assertTrue(h.canMoveTo(5,3));

        assertFalse(h.canMoveTo(2,2));
        assertFalse(h.canMoveTo(4,2));
    }

}
