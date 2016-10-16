package com.parser;

import com.constants.Constants;
import com.string.Sentence;
import com.string.Word;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * @author Ihar Zharykau
 */
public class SentenceParser implements Constants{
    public static List<Sentence> parse(InputStream pIs){
        Scanner s = new Scanner(pIs).useDelimiter("\\A");
        return parse(s);
    }

    public static List<Sentence> parse(String pInputString){
        List<Sentence> sentences = new ArrayList<>();
        Matcher sentenceMatcher = SENTENCE_PATTERN.matcher(pInputString);
        while(sentenceMatcher.find()){
            String stringSentence = sentenceMatcher.group().trim();
            parseStringSentence(stringSentence);
        }
        return sentences;
    }

    public static List<Sentence> parse(Scanner pSc){
        String result = pSc.hasNext() ? pSc.next() : "";
        return parse(result);
    }

    public static List<Word> parseStringSentence(String pSentence){
        List<Word> words = new ArrayList<>();
        Matcher wordMatcher = WORD_PATTERN.matcher(pSentence.trim());
        while(wordMatcher.find()){
            String stringWord = wordMatcher.group().trim();
            System.out.println(stringWord);
        }
        return words;
    }

    public static List<Sentence> parse()

    public static void main(String... args) throws IOException{
            URL oracle = new URL("http://www.google.com/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(Jsoup.parse(inputLine).text());
            }
            in.close();

    }
}
