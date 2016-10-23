package com.example;

import com.exception.TextParseException;
import com.messages.MessageByLocale;
import com.parser.SentenceParser;
import com.parser.WordParser;
import com.string.Sentence;
import com.string.Word;
import com.string.info.WordInfo;
import com.text.TextHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ihar Zharykau
 */
public class MainExample {
    static Logger log = LogManager.getLogger(MainExample.class.getName());

    private static final TextHandler TEXT_HANDLER = new TextHandler();

    public static void main(String... args){
        Locale curLocale = new Locale("en");
        Scanner input = new Scanner(System.in);
        MessageByLocale messages = new MessageByLocale(curLocale);
        System.out.println(messages.getChooseOperationMessage());
        String operation = input.nextLine();
        try {
            switch (operation) {
                case "1":
                    executeFirst(input, messages);
                    break;
                case "2":
                    executeSecond(input, messages);
                    break;
                default:

            }
        }
        catch (TextParseException e){
            log.error(e.getMessage());
            System.err.println("Error while parse text!");
        }
        finally {
            input.close();
        }
    }

    private static void executeFirst(Scanner input, MessageByLocale messages) throws TextParseException {
        System.out.println(messages.getInputFileNameMessage());
        String fileName = input.nextLine();
        System.out.println(messages.getInputCharacterMessage());
        String character = input.nextLine().substring(0, 1);
        List<Word> words = WordParser.parse(new File(fileName));
        TEXT_HANDLER.sortByChar(character.charAt(0), words);
        System.out.println(messages.getResultMessage());
        System.out.println(words);
    }

    private static void executeSecond(Scanner input, MessageByLocale messages) throws TextParseException {
        System.out.println(messages.getInputFileNameMessage());
        String fileName = input.nextLine();
        System.out.println(messages.getInputStringArrayMessage());
        Set<Word> words = new HashSet<>();
        words.addAll(Arrays.asList(input.nextLine().split(" ")).stream()
                .map(Word::new)
                .collect(Collectors.toCollection(ArrayList::new))
        );
        WordInfo wordInfo = new WordInfo(words);
        List<Sentence> sentences = SentenceParser.parse(new File(fileName));
        new TextHandler().sortByCountInText(wordInfo, sentences);
        System.out.println(messages.getResultMessage());
        for (Map.Entry<Word, Integer> entry : wordInfo.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
