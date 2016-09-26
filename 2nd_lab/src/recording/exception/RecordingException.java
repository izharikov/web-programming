package recording.exception;

/**
 * Created by Igor on 26/09/2016.
 */
public class RecordingException extends Exception {
    public RecordingException() {
        super();
    }

    public RecordingException(String message) {
        super(message);
    }

    public RecordingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordingException(Throwable cause) {
        super(cause);
    }

    protected RecordingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
