package com.example;

import com.parser.SentenceParser;
import com.parser.WordParser;
import com.string.Sentence;
import com.string.Word;
import com.string.comparator.WordComparatorByChar;
import com.string.info.WordInfo;
import com.text.TextHandler;

import java.io.File;
import java.util.*;

/**
 * @author Ihar Zharykau
 */
public class MainExample {
    private static final String FILE_NAME = "test.txt";

    public static void main(String... args) {

        List<Sentence> sentences = SentenceParser.parse(new File(FILE_NAME));
        Set<Word> words = new HashSet<>();
        words.add(new Word("hello"));
        words.add(new Word("bye"));
        words.add(new Word("world"));
        words.add(new Word("man"));
        WordInfo wi = new WordInfo(words);
        new TextHandler().sortByCountInText(wi, sentences);
        for(Map.Entry<Word, Integer> entry : wi.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
