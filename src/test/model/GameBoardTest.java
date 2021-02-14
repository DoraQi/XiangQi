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
        // no general on board
        assertTrue(board.checkWin());
        // 1 general on board
        board.createPiece("general [4,0]r");
        assertTrue(board.checkWin());
        // 1 general + another piece on board
        board.createPiece("soldier [2,3]r");
        assertTrue(board.checkWin());
        // 2 generals on board + another piece on board
        board.createPiece("general [3,9]b");
        assertFalse(board.checkWin());
        // 2 generals + 2 other pieces on board
        board.createPiece("horse [2,2]b");
        assertFalse(board.checkWin());
    }

    @Test
    public void testIsEmptyAt() {
        assertTrue(board.isEmptyAt(1, 1));
        board.createPiece("horse [1,1]r");
        assertFalse(board.isEmptyAt(1, 1));
    }

    @Test
    public void testPutPieceGeneral() {
        Piece p = board.createPiece("general [4,0]r");
        assertFalse(board.isEmptyAt(4, 0));
        assertEquals("General", p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceAdvisor() {
        Piece p = board.createPiece("advisor [3,0]r");
        assertFalse(board.isEmptyAt(3, 0));
        assertEquals("Advisor", p.getPieceClass());
        assertEquals(3, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceChariot() {
        Piece p = board.createPiece("chariot [0,9]b");
        assertFalse(board.isEmptyAt(0, 9));
        assertEquals("Chariot", p.getPieceClass());
        assertEquals(0, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceElephant() {
        Piece p = board.createPiece("elephant [2,9]b");
        assertFalse(board.isEmptyAt(2, 9));
        assertEquals("Elephant", p.getPieceClass());
        assertEquals(2, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceHorse() {
        Piece p = board.createPiece("horse [5,5]r");
        assertFalse(board.isEmptyAt(5, 5));
        assertEquals("Horse", p.getPieceClass());
        assertEquals(5, p.getPosX());
        assertEquals(5, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceCannon() {
        Piece p = board.createPiece("cannon [1,7]b");
        assertFalse(board.isEmptyAt(1, 7));
        assertEquals("Cannon", p.getPieceClass());
        assertEquals(1, p.getPosX());
        assertEquals(7, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceSoldier() {
        Piece p = board.createPiece("soldier [4,3]r");
        assertFalse(board.isEmptyAt(4, 3));
        assertEquals("Soldier", p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(3, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceIllegalInputFormat1() {
        try {
            board.createPiece("ahsbdj");
            fail();
        } catch (Exception ignored) {

        }
    }
    
    @Test
    public void testPutPieceIllegalPieceClass() {
        try {
            board.createPiece("brook [0,0]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalInputFormat2() {
        try {
            board.createPiece("soldier [1, 4]r");
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat3() {
        try {
            board.createPiece("soldier 14r");
            fail();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalXTooLarge() {
        try {
            board.createPiece("soldier [9,2]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalXTooSmall() {
        try {
            board.createPiece("soldier [-1,2]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooLarge() {
        try {
            board.createPiece("soldier [2,10]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooSmall() {
        try {
            board.createPiece("soldier [2,-3]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalXYPosition() {
        try {
            board.createPiece("soldier [-3,10]r");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testToString() {
        assertEquals("", board.toString());

        board.createPiece("advisor [3,0]r");
        assertEquals("Advisor[3, 0]R", board.toString());
        board.createPiece("general [4,9]b");
        assertEquals("Advisor[3, 0]R \nGeneral[4, 9]B", board.toString());
    }

    @Test
    public void testPlacePiece() {
        Piece p = new Advisor(3, 0, new GameBoard(), true);
        board.placePiece(p);
        assertFalse(board.isEmptyAt(3, 0));
        assertEquals(p, board.getPAt(3, 0));
    }

    @Test
    public void testSetUpClassicGame() {
        board.setUpClassicGame();
        for (int i = 0; i <= 8; i++) {
            assertFalse(board.isEmptyAt(i, 0));
            assertFalse(board.isEmptyAt(i, 9));
            if (i % 2 == 0) {
                assertFalse(board.isEmptyAt(i, 3));
                assertFalse(board.isEmptyAt(i, 6));
            }
            if (i == 1 || i == 7) {
                assertFalse(board.isEmptyAt(i, 2));
            }
        }
    }

    @Test
    public void testRedMoveNoPieceSelected() {
        try {
            board.redMove("00 03");
            fail();
        } catch (NullPointerException ignored) {

        }
        
    }

    @Test
    public void testRedMoveSelectedBlackPiece() {
        board.createPiece("soldier [3,4]b");
        try {
            board.redMove("34 35");
            fail();
        } catch (IllegalArgumentException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testRedMoveToEmptySpot() {
        board.createPiece("soldier [3,4]r");
        Piece p = board.getPAt(3, 4);
        board.redMove("34 35");
        assertTrue(board.isEmptyAt(3, 4));
        assertFalse(board.isEmptyAt(3, 5));
        assertEquals(p, board.getPAt(3, 5));
        assertEquals(3, p.getPosX());
        assertEquals(5, p.getPosY());
    }

    @Test
    public void testRedMoveToEmptySpotCannonMoveTo() {
        board.createPiece("soldier [3,4]r");
        Piece p = board.getPAt(3, 4);
        try {
            board.redMove("34 37");
        } catch (IllegalArgumentException iae) {
            assertEquals(p, board.getPAt(3, 4));
            assertTrue(board.isEmptyAt(3, 7));
            assertEquals(3, p.getPosX());
            assertEquals(4, p.getPosY());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testRedMoveToSpotFilledByRedP() {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,5]r");
        Piece p2 = board.getPAt(3, 5);
        try {
            board.redMove("34 35");
        } catch (IllegalArgumentException ignored) {
            assertEquals(p1, board.getPAt(3, 4));
            assertEquals(p2, board.getPAt(3, 5));
            assertEquals(3, p1.getPosX());
            assertEquals(4, p1.getPosY());
            assertEquals(3, p2.getPosX());
            assertEquals(5, p2.getPosY());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testRedMoveToSpotFilledByRedCannontCapture() {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,7]r");
        Piece p2 = board.getPAt(3, 7);
        try {
            board.redMove("34 37");
        } catch (IllegalArgumentException ignored) {
            assertEquals(p1, board.getPAt(3, 4));
            assertEquals(p2, board.getPAt(3, 7));
            assertEquals(3, p1.getPosX());
            assertEquals(4, p1.getPosY());
            assertEquals(3, p2.getPosX());
            assertEquals(7, p2.getPosY());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testRedMoveToSpotFilledByBlackCannontCapture() {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,7]b");
        Piece p2 = board.getPAt(3, 7);
        try {
            board.redMove("34 37");
        } catch (IllegalArgumentException ignored) {
            assertEquals(p1, board.getPAt(3, 4));
            assertEquals(p2, board.getPAt(3, 7));
            assertEquals(3, p1.getPosX());
            assertEquals(4, p1.getPosY());
            assertEquals(3, p2.getPosX());
            assertEquals(7, p2.getPosY());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testRedMoveSpotFilledByBlackCanCapture() {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,5]b");
        Piece p2 = board.getPAt(3, 5);
        board.redMove("34 35");
        assertTrue(board.isEmptyAt(3, 4));
        assertEquals(p1, board.getPAt(3, 5));
        assertEquals(3, p1.getPosX());
        assertEquals(5, p1.getPosY());
    }

    @Test
    public void testBlackMoveSpotFilledByRedCanCapture() {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,5]b");
        Piece p2 = board.getPAt(3, 5);
        board.blackMove("35 34");
        assertTrue(board.isEmptyAt(3, 5));
        assertEquals(p2, board.getPAt(3, 4));
        assertEquals(3, p2.getPosX());
        assertEquals(4, p2.getPosY());
    }
}