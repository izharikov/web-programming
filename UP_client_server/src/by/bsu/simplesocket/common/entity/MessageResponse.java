package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;
import by.bsu.simplesocket.common.enums.ResponseInfo;
import java.text.MessageFormat;

public abstract class MessageResponse extends Message {

    private static final long serialVersionUID = 7419413076105579018L;

    protected ResponseInfo info;
    protected MessageResponse(MessageType messageType) {
        super(messageType, null);
    }

    protected MessageResponse(MessageType messageType, ResponseInfo code, String userName) {
        super(messageType, userName);
        this.info = code;
    }

    public ResponseInfo getInfo() {
        return info;
    }

    public void setInfo(ResponseInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}: {1}",
                info.getCode(),
                info.getDescription());
    }
    public abstract Object toStringForClient();
}
