package exception;

/**
 * Represents an exception to be thrown when the user wants to perform a move that's invalid to XiangQi
 */
public class IllegalInputException extends Exception {
    // EFFECTS: instantiates the exception with default message
    public IllegalInputException() {
        super("<invalid input>");
    }

    // EFFECTS: instantiates the exception with the given message
    public IllegalInputException(String msg) {
        super(msg);
    }
}
