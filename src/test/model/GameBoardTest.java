package model;

import model.components.GameBoard;
import model.pieces.Advisor;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    GameBoard board;

    @BeforeEach
    public void setUp() {
        board = new GameBoard();
    }

    @Test
    public void testCheckWin() {
        // 1 general on board
        board.putPiece("general [4,0]r");
        assertTrue(board.checkWin());
        // 1 general + another piece on board
        board.putPiece("soldier [2,3]r");
        assertTrue(board.checkWin());
        // 2 generals on board + another piece on board
        board.putPiece("general [3,9]b");
        assertFalse(board.checkWin());
        // 2 generals + 2 other pieces on board
        board.putPiece("horse [2,2]b");
        assertFalse(board.checkWin());
    }

    @Test
    public void testIsEmptyAt() {
        assertTrue(board.isEmptyAt(1, 1));
        board.putPiece("horse [1,1]r");
        assertFalse(board.isEmptyAt(1, 1));
    }

    @Test
    public void testPutPieceGeneral() {
        board.putPiece("general [4,0]r");
        assertFalse(board.isEmptyAt(4, 0));
        Piece p = board.getPAt(4, 0);
        assertEquals("General", p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceAdvisor() {
        board.putPiece("advisor [3,0]r");
        assertFalse(board.isEmptyAt(3, 0));
        Piece p = board.getPAt(3, 0);
        assertEquals("Advisor", p.getPieceClass());
        assertEquals(3, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceChariot() {
        board.putPiece("chariot [0,9]b");
        assertFalse(board.isEmptyAt(0, 9));
        Piece p = board.getPAt(0, 9);
        assertEquals("Chariot", p.getPieceClass());
        assertEquals(0, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceElephant() {
        board.putPiece("elephant [2,9]b");
        assertFalse(board.isEmptyAt(2, 9));
        Piece p = board.getPAt(2, 9);
        assertEquals("Elephant", p.getPieceClass());
        assertEquals(2, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceHorse() {
        board.putPiece("horse [5,5]r");
        assertFalse(board.isEmptyAt(5, 5));
        Piece p = board.getPAt(5, 5);
        assertEquals("Horse", p.getPieceClass());
        assertEquals(5, p.getPosX());
        assertEquals(5, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceCannon() {
        board.putPiece("cannon [1,7]b");
        assertFalse(board.isEmptyAt(1, 7));
        Piece p = board.getPAt(1, 7);
        assertEquals("Cannon", p.getPieceClass());
        assertEquals(1, p.getPosX());
        assertEquals(7, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceSoldier() {
        board.putPiece("soldier [4,3]r");
        assertFalse(board.isEmptyAt(4, 3));
        Piece p = board.getPAt(4, 3);
        assertEquals("Soldier", p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(3, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceIllegalInputFormat1() {
        try {
            board.putPiece("ahsbdj");
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat2() {
        try {
            board.putPiece("soldier [1, 4]r");
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat3() {
        try {
            board.putPiece("soldier 14r");
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalXTooLarge() {
        try {
            board.putPiece("soldier [9,2]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalXTooSmall() {
        try {
            board.putPiece("soldier [-1,2]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooLarge() {
        try {
            board.putPiece("soldier [2,10]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooSmall() {
        try {
            board.putPiece("soldier [2,-3]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalXYPosition() {
        try {
            board.putPiece("soldier [-3,10]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testToString() {
        assertEquals("", board.toString());

        board.putPiece("advisor [3,0]r");
        assertEquals("Advisor[3, 0]R", board.toString());
        board.putPiece("general [4,9]b");
        assertEquals("Advisor[3, 0]R \nGeneral[4, 9]B", board.toString());
    }

    @Test
    public void testPlacePiece() {
        Piece p = new Advisor(3, 0, new GameBoard(), true);
        board.placePiece(p);
        assertFalse(board.isEmptyAt(3, 0));
        assertEquals(p, board.getPAt(3, 0));
    }

}