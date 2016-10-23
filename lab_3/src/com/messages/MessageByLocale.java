package com.messages;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Ihar Zharykau
 */
public class MessageByLocale {
    private ResourceBundle resourceBundle;

    private static final String MESSAGE_LOCATION = "messages/messages";
    private static final String CHOOSE_MESSAGE = "chooseOperation";
    private static final String INPUT_MESSAGE = "inputFileName";
    private static final String RESULT_MESSAGE = "resultMessage";

    public MessageByLocale(Locale currentLocale) {
        resourceBundle = ResourceBundle.getBundle(MESSAGE_LOCATION, currentLocale);
    }

    public String getChooseOperationMessage(){
        return resourceBundle.getString(CHOOSE_MESSAGE);
    }

    public String getInputFileNameMessage(){
        return resourceBundle.getString(INPUT_MESSAGE);
    }

    public String getResultMessage(){
        return resourceBundle.getString(RESULT_MESSAGE);
    }

    public String getInputCharacterMessage(){
        return "Please, input character to sort:";
    }

    public String getInputStringArrayMessage(){
        return "Enter words to sort, split by space:";
    }
}
