package model;


import model.components.GameBoard;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierTest extends PieceTest{
    @BeforeEach
    public void setup() {
        board = new GameBoard();
        redP = new Soldier(2, 4, board, true);
        blackP = new Soldier(3, 7, board, false);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, redP.getPosX());
        assertEquals(3, blackP.getPosX());
        assertEquals(4, redP.getPosY());
        assertEquals(7, blackP.getPosY());
        assertTrue(redP.isRed());
        assertFalse(blackP.isRed());
        assertEquals(redP, board.getPAt(2, 4));
        assertEquals(blackP, board.getPAt(3, 7));
    }

    @Test
    public void testCanMoveBfCrossingRiverRed() {
        assertTrue(redP.canMoveTo(2, 5));
        assertFalse(redP.canMoveTo(2, 4));
        assertFalse(redP.canMoveTo(3, 4));
        assertFalse(redP.canMoveTo(1, 4));
        assertFalse(redP.canMoveTo(2, 6));
        assertFalse(redP.canMoveTo(2, 3));
    }

    @Test
    public void testCanMoveBfCrossingRiverBlack() {
        assertTrue(blackP.canMoveTo(3, 6));
        assertFalse(blackP.canMoveTo(3, 7));
        assertFalse(blackP.canMoveTo(2, 7));
        assertFalse(blackP.canMoveTo(4, 7));
        assertFalse(blackP.canMoveTo(3, 8));
        assertFalse(blackP.canMoveTo(3, 5));
    }

    @Test
    public void testCanMoveAftCrossingRiverRed() {
        redP.move(4,6);

        // moving one grid
        assertTrue(redP.canMoveTo(4, 7));
        assertTrue(redP.canMoveTo(3, 6));
        assertTrue(redP.canMoveTo(5, 6));
        assertFalse(redP.canMoveTo(3, 7));
        assertFalse(redP.canMoveTo(4,5));
        assertFalse(redP.canMoveTo(5,7));
        assertFalse(redP.canMoveTo(4,6));

        // try to move two grids
        assertFalse(redP.canMoveTo(2,6));
        assertFalse(redP.canMoveTo(6,6));
        assertFalse(redP.canMoveTo(2,8));
    }

    @Test
    public void testCanMoveAftCrossingRiverBlack() {
        blackP.move(3, 3);

        // moving one grid
        assertTrue(blackP.canMoveTo(3, 2));
        assertTrue(blackP.canMoveTo(4, 3));
        assertTrue(blackP.canMoveTo(2, 3));
        assertFalse(blackP.canMoveTo(3,4));
        assertFalse(blackP.canMoveTo(2,2));
        assertFalse(blackP.canMoveTo(4,4));

        // try to move two grids
        assertFalse(blackP.canMoveTo(5,3));
        assertFalse(blackP.canMoveTo(3,5));
        assertFalse(blackP.canMoveTo(3,1));
    }
}
