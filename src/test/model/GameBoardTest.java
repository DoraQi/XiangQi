package model;

import exception.*;
import model.components.GameBoard;
import model.components.PieceFactory;
import model.pieces.Advisor;
import model.pieces.Horse;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.components.PieceFactory.*;
import static model.pieces.PieceClass.*;
import static model.components.PieceFactory.*;
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
        createPiece("general [4,0]r", board);
        assertTrue(board.checkWin());
        // 1 general + another piece on board
        createPiece("soldier [2,3]r", board);
        assertTrue(board.checkWin());
    }

    @Test
    public void testCheckWin2Generals() throws IllegalInputException, IllegalNumGeneralException {
        createPiece("general [4,0]r", board);
        createPiece("general [3,9]b", board);
        assertFalse(board.checkWin());
        createPiece("horse [2,2]b", board);
        assertFalse(board.checkWin());
    }

    @Test
    public void testCheckWinMoreThan2Generals() throws IllegalInputException {
        try {
            createPiece("general [4,0]r", board);
            createPiece("general [3,9]b", board);
            createPiece("general [3,0]r", board);
            board.checkWin();
            fail();
        } catch (IllegalNumGeneralException ignored) {

        }
    }

    @Test
    public void testIsEmptyAt() throws IllegalInputException {
        assertTrue(board.isEmptyAt(1, 1));
        createPiece("horse [1,1]r", board);
        assertFalse(board.isEmptyAt(1, 1));
    }

    @Test
    public void testPutPieceGeneral() throws IllegalInputException {
        Piece p = createPiece("general [4,0]r", board);
        assertFalse(board.isEmptyAt(4, 0));
        assertEquals(GENERAL, p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceAdvisor() throws IllegalInputException {
        Piece p = createPiece("advisor [3,0]r", board);
        assertFalse(board.isEmptyAt(3, 0));
        assertEquals(ADVISOR, p.getPieceClass());
        assertEquals(3, p.getPosX());
        assertEquals(0, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceChariot() throws IllegalInputException {
        Piece p = createPiece("chariot [0,9]b", board);
        assertFalse(board.isEmptyAt(0, 9));
        assertEquals(CHARIOT, p.getPieceClass());
        assertEquals(0, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceElephant() throws IllegalInputException {
        Piece p = createPiece("elephant [2,9]b", board);
        assertFalse(board.isEmptyAt(2, 9));
        assertEquals(ELEPHANT, p.getPieceClass());
        assertEquals(2, p.getPosX());
        assertEquals(9, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceHorse() throws IllegalInputException {
        Piece p = createPiece("horse [5,5]r", board);
        assertFalse(board.isEmptyAt(5, 5));
        assertEquals(HORSE, p.getPieceClass());
        assertEquals(5, p.getPosX());
        assertEquals(5, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceCannon() throws IllegalInputException {
        Piece p = createPiece("cannon [1,7]b", board);
        assertFalse(board.isEmptyAt(1, 7));
        assertEquals(CANNON, p.getPieceClass());
        assertEquals(1, p.getPosX());
        assertEquals(7, p.getPosY());
        assertFalse(p.isRed());
    }

    @Test
    public void testPutPieceSoldier() throws IllegalInputException {
        Piece p = createPiece("soldier [4,3]r", board);
        assertFalse(board.isEmptyAt(4, 3));
        assertEquals(SOLDIER, p.getPieceClass());
        assertEquals(4, p.getPosX());
        assertEquals(3, p.getPosY());
        assertTrue(p.isRed());
    }

    @Test
    public void testPutPieceIllegalInputFormat1() {
        try {
            createPiece("ahsbdj", board);
            fail();
        } catch (RuntimeException | IllegalInputException ignored) {

        }
    }
    
    @Test
    public void testPutPieceIllegalPieceClass() {
        try {
            createPiece("brook [0,0]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceGeneralOutOfBound() {
        try {
            createPiece("general [0,0]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceAdvisorOutOfBound() {
        try {
            createPiece("advisor [0,0]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceElephantOutOfBound() {
        try {
            createPiece("elephant [0,0]b", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat2() {
        try {
            createPiece("soldier [1, 4]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalInputFormat3() {
        try {
            createPiece("soldier 14r", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalXTooLarge() {
        try {
            createPiece("soldier [9,2]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testPutPieceIllegalXTooSmall() {
        try {
            createPiece("soldier [-1,2]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooLarge() {
        try {
            createPiece("soldier [2,10]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalYTooSmall() {
        try {
            createPiece("soldier [2,-3]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPutPieceIllegalXYPosition() {
        try {
            createPiece("soldier [-3,10]r", board);
            fail();
        } catch (IllegalInputException ignored) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testToString() throws IllegalInputException {
        assertEquals("", board.toString());

        createPiece("advisor [3,0]r", board);
        assertEquals("Advisor[3, 0]R", board.toString());
        createPiece("general [4,9]b", board);
        assertEquals("Advisor[3, 0]R \nGeneral[4, 9]B", board.toString());
    }

    @Test
    public void testPlacePiece() throws OutOfBoundPositionException, LocationOccupiedException {
        Piece p = new Advisor(3, 0, new GameBoard(), true);
        board.placePiece(p);
        assertFalse(board.isEmptyAt(3, 0));
        assertEquals(p, board.getPAt(3, 0));
    }

    @Test
    public void testPlacePieceDuplicate() throws OutOfBoundPositionException {
        Piece p = null;
        try {
            p = new Advisor(3, 0, board, true);
            new Horse(3, 0, board, true);
            fail();
        } catch (LocationOccupiedException e) {
            assertEquals(p, board.getPAt(3, 0));
        }
        
    }

    @Test
    public void testSetUpClassicGame() {
        setUpClassicGame(board);
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
    public void testPlayerNoPieceSelected() {
        try {
            board.makeMove(0, 0, 0, 3, true);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }
    
    @Test
    public void testRedMoveSelectedBlackPiece() throws IllegalInputException {
        createPiece("soldier [3,4]b", board);
        try {
            board.makeMove(3, 4, 3, 5, true);
            fail();
        } catch (IllegalInputException ignored) {

        }
    }

    @Test
    public void testRedMoveToEmptySpot() throws IllegalInputException {
        createPiece("soldier [3,4]b", board);
        Piece p = board.getPAt(3, 4);
        board.makeMove(3, 3, 4, 3, false);
        assertTrue(board.isEmptyAt(3, 4));
        assertFalse(board.isEmptyAt(3, 3));
        assertSame(p, board.getPAt(3, 3));
        assertEquals(3, p.getPosX());
        assertEquals(3, p.getPosY());
    }

    @Test
    public void testPlayerMoveToEmptySpotCannotMoveTo() throws IllegalInputException {
        createPiece("soldier [3,4]r", board);
        Piece p = board.getPAt(3, 4);
        try {
            board.makeMove(3, 4, 3, 7, true);
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
        createPiece("soldier [3,4]r", board);
        Piece p1 = board.getPAt(3, 4);
        createPiece("soldier [3,5]r", board);
        Piece p2 = board.getPAt(3, 5);
        try {
            board.makeMove(3, 4,  3, 5, true);
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
        createPiece("soldier [3,4]r", board);
        Piece p1 = board.getPAt(3, 4);
        createPiece("soldier [3,7]r", board);
        Piece p2 = board.getPAt(3, 7);
        try {
            board.makeMove(3, 4,  3, 7, true);
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
        createPiece("soldier [3,4]r", board);
        Piece p1 = board.getPAt(3, 4);
        createPiece("soldier [3,7]b", board);
        Piece p2 = board.getPAt(3, 7);
        try {
            board.makeMove(3, 4, 3, 7, true);
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
    public void testRedMoveSpotFilledByBlackCanCapture() throws IllegalInputException {
        createPiece("soldier [3,4]r", board);
        Piece p1 = board.getPAt(3, 4);
        createPiece("soldier [3,5]b", board);
        Piece p2 = board.getPAt(3, 5);
        board.makeMove(3, 3, 4, 5, true);
        assertTrue(board.isEmptyAt(3, 4));
        assertEquals(p1, board.getPAt(3, 5));
        assertEquals(3, p1.getPosX());
        assertEquals(5, p1.getPosY());
    }

    @Test
    public void testEqualsSameObj() {
        assertEquals(board, board);
    }

    @Test
    public void testEqualsDiffObjSameEmptyFields() {
        assertEquals(new GameBoard(), board);
    }

    @Test
    public void testEqualsDiffObjSameFields() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        makeNewPiece("horse", 3, 3, b2, true);
        assertEquals(b2, board);
    }

    @Test
    public void testEqualsDiffObjDiffNumPieces() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        assertNotEquals(b2, board);
    }

    @Test
    public void testEqualsDiffObjSamePosDiffPieces() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        makeNewPiece("cannon", 3, 3, b2, true);
        assertNotEquals(b2, board);
    }

    @Test
    public void testEqualsDiffObjDiffPos() throws IllegalInputException {
        GameBoard b2 = new GameBoard();
        makeNewPiece("horse", 3, 3, board, true);
        makeNewPiece("cannon", 4, 3, b2, true);
        assertNotEquals(b2, board);
    }
}