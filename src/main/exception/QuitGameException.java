package exception;

public class QuitGameException extends Exception {
    private final boolean redGoesNext;

    public QuitGameException(boolean redGoesNext) {
        this.redGoesNext = redGoesNext;
    }

    public boolean redGoesNext() {
        return redGoesNext;
    }
}
