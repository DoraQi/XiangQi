package model;

import exception.IllegalInputException;
import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import model.pieces.Cannon;
import model.pieces.Chariot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.components.PieceFactory.makeNewPiece;
import static org.junit.jupiter.api.Assertions.*;

public class ChariotTest extends PieceTest{
    @BeforeEach
    public void setup() throws LocationOccupiedException, OutOfBoundPositionException {
        board = new GameBoard();
        redP = new Chariot(1, 2, board, true);
        blackP = new Chariot(4, 7, board, false);
    }

    @Test
    public void testConstructor() {
        assertEquals(redP, board.getPAt(1, 2));
        assertEquals(blackP, board.getPAt(4, 7));
        assertTrue(redP.isRed());
        assertFalse(blackP.isRed());
        assertEquals(1, redP.getPosX());
        assertEquals(2, redP.getPosY());
        assertEquals(4, blackP.getPosX());
        assertEquals(7, blackP.getPosY());
    }

    @Test
    public void testCanMoveToNoBlock() {
        assertTrue(redP.canMoveTo(4, 2));
        assertTrue(redP.canMoveTo(0, 2));
        assertTrue(redP.canMoveTo(1, 0));
        assertTrue(redP.canMoveTo(1, 7));

        assertFalse(redP.canMoveTo(2, 3));
        assertFalse(redP.canMoveTo(0, 3));
        assertFalse(redP.canMoveTo(2, 0));
        assertFalse(redP.canMoveTo(0, 0));
    }

    @Test
    public void testCanMoveToBlocked() throws LocationOccupiedException, OutOfBoundPositionException {
        new Chariot(1, 6, board, false);
        new Chariot(5, 2, board, true);

        assertTrue(redP.canMoveTo(1, 5));
        assertFalse(redP.canMoveTo(1, 7));
        assertFalse(redP.canMoveTo(1, 8));

        assertTrue(redP.canMoveTo(4, 2));
        assertFalse(redP.canMoveTo(6, 2));
        assertFalse(redP.canMoveTo(7, 2));
    }


    @Test
    public void testEqualsNoFieldsSame() throws IllegalInputException {
        assertNotEquals(makeNewPiece("horse", 2, 4, board, false), redP);
    }

    @Test
    public void testEqualsSamePieceClassOnly() {
        assertNotEquals(blackP, redP);
    }

    @Test
    public void testEqualsSameXPosOnly() throws IllegalInputException {
        assertNotEquals(makeNewPiece("horse", 1, 3, board, false), redP);
    }

    @Test
    public void testEqualsSameYPosOnly() throws IllegalInputException {
        assertNotEquals(makeNewPiece("horse", 6, 2, board, false), redP);
    }

    @Test
    public void testEqualsSameSideOnly() throws IllegalInputException {
        assertNotEquals(makeNewPiece("horse", 6, 3, board, true), redP);
    }

    @Test
    public void testEqualsMultipleFieldsSame() throws IllegalInputException {
        assertNotEquals(makeNewPiece("chariot", 3, 0, board, true), redP);
        assertNotEquals(makeNewPiece("chariot", 1, 0, board, true), redP);
    }

    @Test
    public void testEqualsSameObj() {
        assertEquals(redP, redP);
        assertEquals(blackP, blackP);
    }

}
