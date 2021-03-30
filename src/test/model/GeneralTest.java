package model;

import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import model.pieces.Advisor;
import model.pieces.General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralTest extends PieceTest{
    @BeforeEach
    public void setup() throws OutOfBoundPositionException, LocationOccupiedException {
        board = new GameBoard();
        redP = new General(4, 1, board, true);
        blackP = new General(4, 9, board, false);
    }

    @Test
    public void testConstructor() {
        assertTrue(redP.isRed());
        assertEquals(4, redP.getPosX());
        assertEquals(1, redP.getPosY());
        assertEquals(redP, board.getPAt(4, 1));
    }


    @Test
    public void testConstructorOutOfPalace() {
        try {
            General a1 = new General(0, 0, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testConstructorOutOfPalace2() {
        try {
            General a1 = new General(4, 7, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testConstructorOutOfPalace3() {
        try {
            General a1 = new General(4, 0, board, false);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testConstructorOutOfPalace4() {
        try {
            General a1 = new General(7, 0, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testCanMoveToInPalaceOneGrid() {
        assertTrue(redP.canMoveTo(4, 2));
        assertTrue(redP.canMoveTo(4, 0));
        assertTrue(redP.canMoveTo(3, 1));
        assertTrue(redP.canMoveTo(5, 1));
        assertFalse(redP.canMoveTo(4, 1));
        assertFalse(redP.canMoveTo(3, 0));
        assertFalse(redP.canMoveTo(3, 2));
        assertFalse(redP.canMoveTo(5, 0));
        assertFalse(redP.canMoveTo(5, 2));
    }

    @Test
    public void testCanMoveToInPalaceMultipleGrids() throws OutOfBoundPositionException {
        try {
            redP = new General(3, 0, board, true);
            assertFalse(redP.canMoveTo(5, 0));
            assertFalse(redP.canMoveTo(3, 2));
            assertFalse(redP.canMoveTo(5, 2));

            redP = new General(5, 2, board, true);
            assertFalse(redP.canMoveTo(3, 2));
            assertFalse(redP.canMoveTo(5, 0));
            assertFalse(redP.canMoveTo(3, 0));
        } catch (LocationOccupiedException e) {
            fail();
        }

    }

    @Test
    public void testCanMoveToOutOfPalace() throws OutOfBoundPositionException, LocationOccupiedException {
        redP = new General(3, 2, board, true);
        assertFalse(redP.canMoveTo(2, 2));
        assertFalse(redP.canMoveTo(2, 3));

        redP = new General(5, 2, board, true);
        assertFalse(redP.canMoveTo(5, 3));
        assertFalse(redP.canMoveTo(6, 2));

        blackP = new General(3, 7, board, false);
        assertFalse(blackP.canMoveTo(2, 7));
        assertFalse(blackP.canMoveTo(3, 6));

        blackP = new General(5, 7, board, false);
        assertFalse(blackP.canMoveTo(6, 7));
        assertFalse(blackP.canMoveTo(5, 6));
    }

    @Test
    public void testCanCaptureInPalace() {
        assertTrue(redP.canCapture(4, 2));
        assertTrue(redP.canCapture(4, 0));
        assertTrue(redP.canCapture(3, 1));
        assertTrue(redP.canCapture(5, 1));
        assertFalse(redP.canCapture(3, 0));
        assertFalse(redP.canCapture(3, 2));
    }

    @Test
    public void testCanCaptureOppGeneral() throws OutOfBoundPositionException, LocationOccupiedException {
        assertTrue(redP.canCapture(4, 9));

        new Advisor(4, 8, board, false);
        assertFalse(redP.canCapture(4, 9));
        assertFalse(redP.canCapture(4, 8));
    }

}
