package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;
import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 6163445697747497937L;

    private final MessageType messageType;
    protected String userName;

    public String getUserName() {
        return userName;
    }

    protected Message(MessageType messageType, String userName) {
        this.messageType = messageType;
        this.userName = userName;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
