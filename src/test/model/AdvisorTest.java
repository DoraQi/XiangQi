package model;

import model.components.GameBoard;
import model.pieces.Advisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdvisorTest extends PieceTest{

    @BeforeEach
    public void setup() {
        board = new GameBoard();
        redP = new Advisor(4, 1, board, true);
        blackP = new Advisor(4, 8, board, false);

        assertTrue(redP.isRed());
        assertFalse(blackP.isRed());
        assertEquals(4, redP.getPosX());
        assertEquals(1, redP.getPosY());
        assertEquals(4, blackP.getPosX());
        assertEquals(8, blackP.getPosY());
    }

    @Test
    public void testCanMoveToInPalaceRed() {
        assertTrue(redP.canMoveTo(3, 2));
        assertTrue(redP.canMoveTo(3, 0));
        assertTrue(redP.canMoveTo(5, 2));
        assertTrue(redP.canMoveTo(5, 0));

        assertFalse(redP.canMoveTo(4, 2));
        assertFalse(redP.canMoveTo(4, 0));
        assertFalse(redP.canMoveTo(3, 1));
        assertFalse(redP.canMoveTo(5, 1));
    }

    @Test
    public void testCanMoveToInPalaceBlack() {
        assertTrue(blackP.canMoveTo(3, 9));
        assertTrue(blackP.canMoveTo(3, 7));
        assertTrue(blackP.canMoveTo(5, 9));
        assertTrue(blackP.canMoveTo(5, 7));

        assertFalse(blackP.canMoveTo(4, 9));
        assertFalse(blackP.canMoveTo(4, 7));
        assertFalse(blackP.canMoveTo(3, 8));
        assertFalse(blackP.canMoveTo(5, 8));
    }

    @Test
    public void testCanMoveToOutPalaceRed() {
        redP.move(3, 2);
        assertFalse(redP.canMoveTo(2, 3));
        assertFalse(redP.canMoveTo(4, 3));
        assertFalse(redP.canMoveTo(2, 1));

        redP.move(5, 2);
        assertFalse(redP.canMoveTo(4, 3));
        assertFalse(redP.canMoveTo(6, 3));
        assertFalse(redP.canMoveTo(6, 1));
    }

    @Test
    public void testCanMoveToOutPalaceBlack() {
        redP.move(3, 7);
        assertFalse(redP.canMoveTo(2, 8));
        assertFalse(redP.canMoveTo(4, 6));
        assertFalse(redP.canMoveTo(2, 6));

        redP.move(5, 7);
        assertFalse(redP.canMoveTo(4, 6));
        assertFalse(redP.canMoveTo(6, 6));
        assertFalse(redP.canMoveTo(6, 8));
    }


}
