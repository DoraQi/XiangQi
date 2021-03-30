package model;

import exception.LocationOccupiedException;
import exception.OutOfBoundPositionException;
import model.components.GameBoard;
import model.components.Player;
import model.pieces.Horse;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player redP;
    Player blackP;

    @BeforeEach
    public void setup() {
        redP = new Player(true);
        blackP = new Player(false);
    }

    @Test
    public void testConstructor() {
        assertTrue(redP.isRed());
        assertFalse(blackP.isRed());
    }

    @Test
    public void testCapture() throws LocationOccupiedException, OutOfBoundPositionException {
        Piece p = new Horse(1, 2, new GameBoard(), false);
        redP.capture(p);
        assertTrue(redP.hasCaptured(p));
    }

    @Test
    public void testAddPiece() throws LocationOccupiedException, OutOfBoundPositionException {
        Piece p = new Horse(1, 2, new GameBoard(), false);
        blackP.addPiece(p);
        assertTrue(blackP.has(p));
    }

    @Test
    public void testRemovePiece() throws LocationOccupiedException, OutOfBoundPositionException {
        Piece p = new Horse(1, 2, new GameBoard(), false);
        blackP.addPiece(p);
        assertTrue(blackP.has(p));

        blackP.removePiece(p);
        assertFalse(blackP.has(p));
    }

    @Test
    public void testGetPieces() throws LocationOccupiedException, OutOfBoundPositionException {
        GameBoard b = new GameBoard();
        ArrayList<Piece> lst = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            Piece p = new Horse(i, i+1, b, false);
            blackP.addPiece(p);
            lst.add(p);
        }
        assertEquals(lst, blackP.getPieces());
    }

    @Test
    public void testIsRed() {
        assertTrue(redP.isRed());
        assertFalse(blackP.isRed());
        Player ply = new Player(true);
        assertTrue(ply.isRed());
        Player ply2 = new Player(false);
        assertFalse(ply2.isRed());
    }

    @Test
    public void testHas() throws LocationOccupiedException, OutOfBoundPositionException {
        Piece p = new Horse(1, 2, new GameBoard(), false);
        blackP.addPiece(p);
        assertTrue(blackP.has(p));
        assertFalse(redP.has(p));
    }

    @Test
    public void testHasCaptured() throws LocationOccupiedException, OutOfBoundPositionException {
        Piece p = new Horse(1, 2, new GameBoard(), false);
        redP.capture(p);
        assertTrue(redP.hasCaptured(p));
        assertFalse(blackP.has(p));
    }
}
