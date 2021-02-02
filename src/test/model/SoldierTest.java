package model;


import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierTest {
    Soldier redS;
    Soldier blackS;

    @BeforeEach
    public void setup() {
        redS = new Soldier(2, 4, true);
        blackS = new Soldier(3, 7, false);

        assertEquals(2, redS.getPosX());
        assertEquals(3, blackS.getPosX());
        assertEquals(4, redS.getPosY());
        assertEquals(7, blackS.getPosY());
        assertTrue(redS.isRed());
        assertFalse(blackS.isRed());
    }
    @Test
    public void testCanMoveBfCrossingRiverRed() {
        assertTrue(redS.canMoveTo(2, 5));
        assertFalse(redS.canMoveTo(2, 4));
        assertFalse(redS.canMoveTo(3, 4));
        assertFalse(redS.canMoveTo(1, 4));
        assertFalse(redS.canMoveTo(2, 6));
        assertFalse(redS.canMoveTo(2, 3));
    }

    @Test
    public void testCanMoveBfCrossingRiverBlack() {
        assertTrue(blackS.canMoveTo(3, 6));
        assertFalse(blackS.canMoveTo(3, 7));
        assertFalse(blackS.canMoveTo(2, 7));
        assertFalse(blackS.canMoveTo(4, 7));
        assertFalse(blackS.canMoveTo(3, 8));
        assertFalse(blackS.canMoveTo(3, 5));
    }

    @Test
    public void testCanMoveAftCrossingRiverRed() {
        redS.move(4,6);
        assertTrue(redS.crossedRiver());

        assertFalse(redS.canMoveTo(4,6));

        // moving one grid
        assertTrue(redS.canMoveTo(3, 6));
        assertTrue(redS.canMoveTo(5, 6));
        assertFalse(redS.canMoveTo(3, 7));
        assertFalse(redS.canMoveTo(4,5));
        assertFalse(redS.canMoveTo(3,7));

        // try to move two grids
        assertFalse(redS.canMoveTo(2,6));
        assertFalse(redS.canMoveTo(6,6));
        assertFalse(redS.canMoveTo(2,8));
    }

    @Test
    public void testCanMoveAftCrossingRiverBlack() {
        blackS.move(3, 3);
        assertTrue(blackS.crossedRiver());

        assertFalse(blackS.canMoveTo(3,3));

        // moving one grid
        assertTrue(blackS.canMoveTo(3, 2));
        assertTrue(blackS.canMoveTo(4, 3));
        assertTrue(blackS.canMoveTo(2, 3));
        assertFalse(blackS.canMoveTo(3,4));
        assertFalse(blackS.canMoveTo(2,2));

        // try to move two grids
        assertFalse(blackS.canMoveTo(5,3));
        assertFalse(blackS.canMoveTo(3,5));
        assertFalse(blackS.canMoveTo(3,1));
    }

    public void testMoveToCurrentPosition() {
        redS.move(1,4);
        assertEquals(1, redS.getPosX());
        assertEquals(4, redS.getPosY());

        blackS.move(3,6);
        assertEquals(3, blackS.getPosX());
        assertEquals(6, blackS.getPosY());
    }
}
