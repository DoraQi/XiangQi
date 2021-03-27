package exception;

/**
 * Represents an exception thrown when the user tries to quit the game
 */
public class QuitGameException extends Exception {
    private final boolean redGoesNext;

    // EFFECTS: instantiates the exception and records which side is moving next for saving purposes
    public QuitGameException(boolean redGoesNext) {
        this.redGoesNext = redGoesNext;
    }

    // getter
    public boolean redGoesNext() {
        return redGoesNext;
    }
}
