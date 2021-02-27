package exception;

public class IllegalInputException extends Exception {
    public IllegalInputException() {
        super("<invalid input>");
    }

    public IllegalInputException(String msg) {
        super(msg);
    }
}
