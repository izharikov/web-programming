package shapes.exception;

/**
 * Created by Igor on 01.09.2016.
 */
public class ShapeException extends Exception {
    public ShapeException() {
        super();
    }

    public ShapeException(String message) {
        super(message);
    }

    public ShapeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShapeException(Throwable cause) {
        super(cause);
    }

    protected ShapeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
