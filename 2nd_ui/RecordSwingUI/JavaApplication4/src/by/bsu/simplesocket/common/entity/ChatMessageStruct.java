package by.bsu.simplesocket.common.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by igor on 21/04/16.
 */
public class ChatMessageStruct {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY HH:mm");
    private String userName, message;

    public ChatMessageStruct(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return userName + " [" + dateFormat.format(new Date()) + "]" + "\t:\t" + message;
    }
}
