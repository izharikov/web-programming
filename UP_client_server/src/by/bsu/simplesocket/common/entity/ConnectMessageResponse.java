package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;
import by.bsu.simplesocket.common.enums.ResponseInfo;

public class ConnectMessageResponse extends MessageResponse {

    private static final long serialVersionUID = 1801058085219990435L;

    public ConnectMessageResponse() {
        super(MessageType.CONNECT_RESPONSE);
    }

    public ConnectMessageResponse(ResponseInfo code, String userName) {
        super(MessageType.CONNECT_RESPONSE, code, userName);
        this.userName = userName;
    }

    @Override
    public String toStringForClient() {
        return "-----" + userName + " " + info.getDescription() + "-----";
    }
}
