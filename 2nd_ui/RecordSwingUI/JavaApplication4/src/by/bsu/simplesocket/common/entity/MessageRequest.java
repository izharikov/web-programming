package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;

public class MessageRequest extends Message {

    private static final long serialVersionUID = 2255831970173420983L;;

    protected MessageRequest(MessageType messageType) {
        super(messageType, null);
    }

    protected MessageRequest(MessageType messageType, String userName) {
        super(messageType, userName);
    }

    public String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }
}
