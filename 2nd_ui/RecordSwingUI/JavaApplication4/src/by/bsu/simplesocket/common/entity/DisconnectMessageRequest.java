package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;

public class DisconnectMessageRequest extends MessageRequest {

    private static final long serialVersionUID = -398420042129139885L;

    public DisconnectMessageRequest() {
        super(MessageType.DISCONNECT_REQUEST);
    }

    public DisconnectMessageRequest(String userName) {
        super(MessageType.DISCONNECT_REQUEST, userName);
    }
}
