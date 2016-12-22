package by.bsu.simplesocket.common.enums;

public enum ResponseInfo {

    OK(200, "connected to server"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    EMPTY_HELLO_TEXT_ERROR(700, "Hello text is empty"),
    USER_ALREADY_CONNECTED(600, "already connected");

    private int code;
    private String description;
    private static String userName;
    ResponseInfo(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
