package by.bsu.simplesocket.common.entity;

import by.bsu.simplesocket.common.enums.MessageType;
import by.bsu.simplesocket.common.enums.ResponseInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessageResponse extends MessageResponse {

    private static final long serialVersionUID = -5465807440355352530L;

    private String message;

    public ChatMessageResponse() {
        super(MessageType.MESSAGE_RESPONSE);
    }

    public ChatMessageResponse(ResponseInfo code) {
        super(MessageType.MESSAGE_RESPONSE, code, null);
    }

    public ChatMessageResponse(ResponseInfo code, String message, String username) {
        super(MessageType.MESSAGE_RESPONSE, code, username);
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String text = super.toString();
        return text + " - " + message;
    }

    @Override
    public ChatMessageStruct toStringForClient() {
        return new ChatMessageStruct(userName, message);
    }

}
