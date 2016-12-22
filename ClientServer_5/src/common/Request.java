package common;

import java.io.Serializable;

/**
 * Request class, used for connect/disconnect client from server
 *
 * @author Ihar Zharykau
 */
public class Request implements Serializable{
    private String userName;
    private Type type;

    public Request(String userName, Type type) {
        this.userName = userName;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Enum, that determines type of {@link Request}
     */
    public enum Type {
        CONNECT, DISCONNECT
    }
}
