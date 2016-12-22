package by.bsu.simplesocket.common.exception;

public class SimpleSocketException extends Exception {

    public SimpleSocketException() {
    }

    public SimpleSocketException(String message) {
        super(message);
    }

    public SimpleSocketException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleSocketException(Throwable cause) {
        super(cause);
    }
}
