package model;

import model.components.GameBoard;
import model.pieces.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class PieceTest {
    Piece redP;
    Piece blackP;
    GameBoard board;

    @Test
    public void testMove() {
        redP.move(1, 3);
        blackP.move(7, 6);
        assertEquals(1, redP.getPosX());
        assertEquals(3, redP.getPosY());
        assertEquals(7, blackP.getPosX());
        assertEquals(6, blackP.getPosY());
    }

    @Test
    public void testCanCapture() {
        assertEquals(redP.canMoveTo(1, 2), redP.canCapture(1, 2));
        assertEquals(redP.canMoveTo(0, 0), redP.canCapture(0, 0));
        assertEquals(blackP.canMoveTo(1, 2), blackP.canCapture(1, 2));
        assertEquals(blackP.canMoveTo(0, 0), blackP.canCapture(0, 0));
    }

    @Test
    public void testToString() {
        assertEquals(redP.getPieceClass().name().charAt(0)
                + redP.getPieceClass().name().substring(1).toLowerCase()
                        + "[" + redP.getPosX() + ", " + redP.getPosY() + "]R", redP.toString());
        assertEquals(blackP.getPieceClass().name().charAt(0)
                        + blackP.getPieceClass().name().substring(1).toLowerCase() + "[" + blackP.getPosX() + ", "
                + blackP.getPosY() + "]B", blackP.toString());

    }
}
