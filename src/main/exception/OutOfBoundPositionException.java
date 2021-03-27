package exception;

/**
 * Represents an exception thrown when user attempts to make a move outside of the bound of where the piece can go
 */
public class OutOfBoundPositionException extends IllegalInputException {
    public OutOfBoundPositionException() {
        super("<position out of bound>");
    }
}
