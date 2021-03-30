package model;

import exception.IllegalInputException;
import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import model.pieces.Advisor;
import model.pieces.Horse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.components.PieceFactory.makeNewPiece;
import static org.junit.jupiter.api.Assertions.*;

public class AdvisorTest extends PieceTest{

    @BeforeEach
    public void setup() throws OutOfBoundPositionException, LocationOccupiedException {
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
    void testConstructorTwoPiecesOnSameLoc() throws OutOfBoundPositionException {
        try {
            new Horse(4, 1, board, false);
            fail();
        } catch (LocationOccupiedException e) {
            assertEquals(redP, board.getPAt(4, 1));
        }
    }

    @Test
    public void testConstructorOutOfPalace() {
        try {
            Advisor a1 = new Advisor(0, 0, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testConstructorOutOfPalace2() {
        try {
            Advisor a1 = new Advisor(4, 7, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testConstructorOutOfPalace3() {
        try {
            Advisor a1 = new Advisor(4, 0, board, false);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
    }

    @Test
    public void testConstructorOutOfPalace4() {
        try {
            Advisor a1 = new Advisor(7, 0, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            fail();
        } catch (OutOfBoundPositionException ignored) {

        }
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
        assertFalse(redP.canMoveTo(4, 1));
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
    public void testCanMoveToOutPalaceRed() throws LocationOccupiedException {
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
    public void testCanMoveToOutPalaceBlack() throws LocationOccupiedException {
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
