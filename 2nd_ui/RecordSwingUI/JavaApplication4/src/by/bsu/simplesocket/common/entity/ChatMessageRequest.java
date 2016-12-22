package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;

public class ChatMessageRequest extends MessageRequest {

    private static final long serialVersionUID = 6396869800256021961L;

    private String helloText;

    public ChatMessageRequest() {
        super(MessageType.MESSAGE_REQUEST);
    }

    public ChatMessageRequest(String userName) {
        super(MessageType.MESSAGE_REQUEST, userName);
    }

    public ChatMessageRequest(String userName, String helloText) {
        super(MessageType.MESSAGE_REQUEST, userName);
        setHelloText(helloText);
    }

    public String getHelloText() {
        return helloText;
    }

    public final void setHelloText(String helloText) {
        this.helloText = helloText;
    }
}
