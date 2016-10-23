package com.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Ihar Zharykau
 */
public class TextParseException extends Exception{

    static Logger log = LogManager.getLogger(TextParseException.class.getName());

    public TextParseException() {
        super();
    }

    public TextParseException(String message) {
        super(message);
        log.error(message);
    }

    public TextParseException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }

    public TextParseException(Throwable cause) {
        super(cause);
        log.error("", cause);
    }

    protected TextParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error(message, cause);
    }
}
