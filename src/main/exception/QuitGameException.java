package exception;

public class QuitGameException extends Exception {
    private boolean redGoesNext;

    public QuitGameException(boolean redGoesNext) {
        this.redGoesNext = redGoesNext;
    }

    public boolean redGoesNext() {
        return redGoesNext;
    }
}
