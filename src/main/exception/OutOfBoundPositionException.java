package exception;

public class OutOfBoundPositionException extends IllegalInputException {
    public OutOfBoundPositionException() {
        super("<position out of bound>");
    }
}
