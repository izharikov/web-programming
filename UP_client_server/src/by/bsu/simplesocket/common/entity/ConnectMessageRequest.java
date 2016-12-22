package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;

public class ConnectMessageRequest extends MessageRequest {

    private static final long serialVersionUID = 3752270161656112541L;

    public ConnectMessageRequest() {
        super(MessageType.CONNECT_REQUEST);
    }

    public ConnectMessageRequest(String userName) {
        super(MessageType.CONNECT_REQUEST, userName);
    }
}
