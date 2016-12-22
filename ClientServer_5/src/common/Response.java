package common;

import java.io.Serializable;

/**
 * Response class, used for connect/disconnect client from server
 *
 * @author Ihar Zharykau
 */
public class Response implements Serializable {
    /**
     * Status of response
     */
    private Status status;

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
