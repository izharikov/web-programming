package fpmi.business.exception;

/**
 * RepositoryException class. Used in {@link fpmi.business.repository.LibraryRepository}
 *
 * @author Ihar Zharykau
 */
public class RepositoryException extends Exception {
    public RepositoryException() {
    }

    private ExceptionCode exceptionCode;

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RepositoryException(Throwable cause, ExceptionCode exceptionCode){
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public RepositoryException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
