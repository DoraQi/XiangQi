package model;

import model.pieces.Advisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdvisorTest {
    Advisor redA;
    Advisor blackA;

    @BeforeEach
    public void setup() {
        redA = new Advisor(4, 1, true);
        blackA = new Advisor(4, 8, false);

        assertTrue(redA.isRed());
        assertFalse(blackA.isRed());
        assertEquals(4, redA.getPosX());
        assertEquals(1, redA.getPosY());
        assertEquals(4, blackA.getPosX());
        assertEquals(8, blackA.getPosY());
    }

    @Test
    public void testCanMoveTo() {}


}
