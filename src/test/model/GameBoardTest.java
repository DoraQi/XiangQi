package model;

import exception.IllegalInputException;
import exception.IllegalNumGeneralException;
import exception.OutOfBoundPositionException;
import exception.QuitGameException;
import model.components.GameBoard;
import model.pieces.Advisor;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.components.PieceFactory.makeNewPiece;
import static model.pieces.PieceClass.*;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    GameBoard board;

    @BeforeEach
    public void setUp() {
        board = new GameBoard();
    }

    @Test
    public void testCheckWinOneGeneral() throws IllegalInputException, IllegalNumGeneralException {
        // no general on board
        assertTrue(board.checkWin());
        // 1 general on board
        board.createPiece("general [4,0]r");
        assertTrue(board.checkWin());
        // 1 general + another piece on board
        board.createPiece("soldier [2,3]r");
        assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWin2Generals() throws IllegalInputException, IllegalNumGeneralException {
        board.createPiece("general [4,0]r");
        board.createPiece("general [3,9]b");
        assertFalse(board.checkWin());
        board.createPiece("horse [2,2]b");
        assertFalse(board.checkWin());
    }

    @Test
    public void testCheckWinMoreThan2Generals() throws IllegalInputException {
        try {
            board.createPiece("general [4,0]r");
            board.createPiece("general [3,9]b");
            board.createPiece("general [3,0]r");
            board.checkWin();
            fail();
        } catch (IllegalNumGeneralException ignored) {

        }
    }

    @Test
    public void testIsEmptyAt() throws IllegalInputException {
        assertTrue(board.isEmptyAt(1, 1));
        board.createPiece("horse [1,1]r");
        assertFalse(board.isEmptyAt(1, 1));
    }

    @Test
    public void testPutPieceGeneral() throws IllegalInputException {
        Piece p = board.createPiece("general [4,0]r");
        assertFalse(board.isEmptyAt(4, 0));
        assertEquals(GENERAL, p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceAdvisor() throws IllegalInputException {
        Piece p = board.createPiece("advisor [3,0]r");
        assertFalse(board.isEmptyAt(3, 0));
        assertEquals(ADVISOR, p.getPieceClass());
        assertEquals(3, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceChariot() throws IllegalInputException {
        Piece p = board.createPiece("chariot [0,9]b");
        assertFalse(board.isEmptyAt(0, 9));
        assertEquals(CHARIOT, p.getPieceClass());
        assertEquals(0, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceElephant() throws IllegalInputException {
        Piece p = board.createPiece("elephant [2,9]b");
        assertFalse(board.isEmptyAt(2, 9));
        assertEquals(ELEPHANT, p.getPieceClass());
        assertEquals(2, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceHorse() throws IllegalInputException {
        Piece p = board.createPiece("horse [5,5]r");
        assertFalse(board.isEmptyAt(5, 5));
        assertEquals(HORSE, p.getPieceClass());
        assertEquals(5, p.getPosX());
        assertEquals(5, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceCannon() throws IllegalInputException {
        Piece p = board.createPiece("cannon [1,7]b");
        assertFalse(board.isEmptyAt(1, 7));
        assertEquals(CANNON, p.getPieceClass());
        assertEquals(1, p.getPosX());
        assertEquals(7, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceSoldier() throws IllegalInputException {
        Piece p = board.createPiece("soldier [4,3]r");
        assertFalse(board.isEmptyAt(4, 3));
        assertEquals(SOLDIER, p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(3, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceIllegalInputFormat1() {
        try {
            board.createPiece("ahsbdj");
            fail();
        } catch (RuntimeException | IllegalInputException ignored) {

        }
    }
    
    @Test
    public void testPutPieceIllegalPieceClass() {
        try {
            board.createPiece("brook [0,0]r");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceGeneralOutOfBound() {
        try {
            board.createPiece("general [0,0]r");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceAdvisorOutOfBound() {
        try {
            board.createPiece("advisor [0,0]r");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceElephantOutOfBound() {
        try {
            board.createPiece("elephant [0,0]b");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat2() {
        try {
            board.createPiece("soldier [1, 4]r");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat3() {
        try {
            board.createPiece("soldier 14r");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalXTooLarge() {
        try {
            board.createPiece("soldier [9,2]r");
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalXTooSmall() {
        try {
            board.createPiece("soldier [-1,2]r");
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooLarge() {
        try {
            board.createPiece("soldier [2,10]r");
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooSmall() {
        try {
            board.createPiece("soldier [2,-3]r");
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalXYPosition() {
        try {
            board.createPiece("soldier [-3,10]r");
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testToString() throws IllegalInputException {
        assertEquals("", board.toString());

        board.createPiece("advisor [3,0]r");
        assertEquals("Advisor[3, 0]R", board.toString());
        board.createPiece("general [4,9]b");
        assertEquals("Advisor[3, 0]R \nGeneral[4, 9]B", board.toString());
    }

    @Test
    public void testPlacePiece() throws OutOfBoundPositionException {
        Piece p = new Advisor(3, 0, new GameBoard(), true);
        board.placeNewPiece(p);
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
    public void testPlayerNoPieceSelected() throws QuitGameException {
        try {
            board.playerMove("00 03", true);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPlayerMoveQuit() throws IllegalInputException {
        try {
            board.playerMove("quit", false);
            fail();
        } catch (QuitGameException e) {
            assertFalse(e.redGoesNext());
        }
    }

    @Test
    public void testRedMoveSelectedBlackPiece() throws QuitGameException, IllegalInputException {
        board.createPiece("soldier [3,4]b");
        try {
            board.playerMove("34 35", true);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testRedMoveToEmptySpot() throws IllegalInputException, QuitGameException {
        board.createPiece("soldier [3,4]b");
        Piece p = board.getPAt(3, 4);
        board.playerMove("34 33", false);
        assertTrue(board.isEmptyAt(3, 4));
        assertFalse(board.isEmptyAt(3, 3));
        assertSame(p, board.getPAt(3, 3));
        assertEquals(3, p.getPosX());
        assertEquals(3, p.getPosY());
    }

    @Test
    public void testPlayerMoveToEmptySpotCannotMoveTo() throws IllegalInputException {
        board.createPiece("soldier [3,4]r");
        Piece p = board.getPAt(3, 4);
        try {
            board.playerMove("34 37", true);
        } catch (IllegalInputException iae) {
            assertEquals(p, board.getPAt(3, 4));
            assertTrue(board.isEmptyAt(3, 7));
            assertEquals(3, p.getPosX());
            assertEquals(4, p.getPosY());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPlayerMoveToSpotFilledBySameColour() throws IllegalInputException {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,5]r");
        Piece p2 = board.getPAt(3, 5);
        try {
            board.playerMove("34 35", true);
        } catch (IllegalInputException ic) {
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
    public void testRedMoveToSpotFilledByRedCannotCapture() throws IllegalInputException {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,7]r");
        Piece p2 = board.getPAt(3, 7);
        try {
            board.playerMove("34 37", true);
        } catch (IllegalInputException ignored) {
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
    public void testRedMoveToSpotFilledByBlackCannotCapture() throws IllegalInputException {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,7]b");
        Piece p2 = board.getPAt(3, 7);
        try {
            board.playerMove("34 37", true);
        } catch (IllegalInputException ie) {
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
    public void testRedMoveSpotFilledByBlackCanCapture() throws IllegalInputException, QuitGameException {
        board.createPiece("soldier [3,4]r");
        Piece p1 = board.getPAt(3, 4);
        board.createPiece("soldier [3,5]b");
        Piece p2 = board.getPAt(3, 5);
        board.playerMove("34 35", true);
        assertTrue(board.isEmptyAt(3, 4));
        assertEquals(p1, board.getPAt(3, 5));
        assertEquals(3, p1.getPosX());
        assertEquals(5, p1.getPosY());
    }

    @Test
    public void testEqualsSameObj() {
        assertTrue(board.equals(board));
    }

    @Test
    public void testEqualsDiffObjSameEmptyFields() {
        assertTrue(board.equals(new GameBoard()));
    }

    @Test
    public void testEqualsDiffObjSameFields() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        makeNewPiece("horse", 3, 3, b2, true);
        assertTrue(board.equals(b2));
    }

    @Test
    public void testEqualsDiffObjDiffNumPieces() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        assertFalse(board.equals(b2));
    }

    @Test
    public void testEqualsDiffObjSamePosDiffPieces() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        makeNewPiece("cannon", 3, 3, b2, true);
        assertFalse(board.equals(b2));
    }

    @Test
    public void testEqualsDiffObjDiffPos() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        makeNewPiece("cannon", 4, 3, b2, true);
        assertFalse(board.equals(b2));
    }
}