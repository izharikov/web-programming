package com.parser;

import com.constants.Constants;
import com.exception.TextParseException;
import com.string.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * @author Ihar Zharykau
 */
public class WordParser implements Constants{

    public static List<Word> parse(String pSourceString){
        List<Word> words = new ArrayList<>();
        Matcher wordMatcher = WORD_PATTERN.matcher(pSourceString.trim());
        while(wordMatcher.find()){
            String stringWord = wordMatcher.group().trim();
            words.add(new Word(stringWord.toLowerCase()));
        }
        return words;
    }

    public static List<Word> parse(InputStream pInputStream){
        Scanner sc = new Scanner(pInputStream);
        List<Word> result = parse(sc);
        sc.close();
        return result;
    }

    public static List<Word> parse(Scanner pSc){
        pSc.useDelimiter("\\A");
        String result = pSc.hasNext() ? pSc.next() : "";
        return parse(result);
    }

    public static List<Word> parse(File pFile) throws TextParseException {
        try {
            InputStream inputStream = new FileInputStream(pFile);
            List<Word> res = parse(inputStream);
            inputStream.close();
            return res;
        }
        catch (IOException e){
            throw new TextParseException(e);
        }
    }
}
