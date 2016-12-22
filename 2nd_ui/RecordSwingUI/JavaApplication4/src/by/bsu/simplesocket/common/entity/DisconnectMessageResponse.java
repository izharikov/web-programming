package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;
import by.bsu.simplesocket.common.enums.ResponseInfo;

public class DisconnectMessageResponse extends MessageResponse {

    private static final long serialVersionUID = -2363989154828079404L;

    public DisconnectMessageResponse() {
        super(MessageType.DISCONNECT_RESPONSE);
    }

    public DisconnectMessageResponse(ResponseInfo code, String userName) {
        super(MessageType.CONNECT_RESPONSE, code, userName);
    }

    @Override
    public String toStringForClient() {
        return "-----" + userName + " disconnected-----";
    }
}
