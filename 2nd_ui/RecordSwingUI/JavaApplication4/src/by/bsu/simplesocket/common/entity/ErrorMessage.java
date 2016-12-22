package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;
import by.bsu.simplesocket.common.enums.ResponseInfo;

public class ErrorMessage extends MessageResponse {

    private static final long serialVersionUID = 3459466532928769157L;

    public ErrorMessage() {
        super(MessageType.ERROR);
    }
    
    public ErrorMessage(ResponseInfo info) {
        super(MessageType.ERROR, info, null);
    }

    @Override
    public String toStringForClient() {
        return null;
    }
}
