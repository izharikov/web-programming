package common;

import java.io.Serializable;

/**
 * Status enum. Used in {@link Response}.
 *
 * @author Ihar Zharykau
 */
public enum Status implements Serializable {
    /**
     * Success connection
     */
    OK(200, "Success connection"),

    /**
     * Determines server error
     */
    INTERNAL_SERVER_ERROR(500, "Internal server error"),

    /**
     * Determines, that user with the same name already connected
     */
    USER_ALREADY_CONNECTED(600, "User already connected");

    private int code;
    private String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
