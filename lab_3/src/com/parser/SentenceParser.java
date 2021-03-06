package com.parser;

import com.constants.Constants;
import com.exception.TextParseException;
import com.string.Sentence;
import org.jsoup.Jsoup;

import java.io.*;
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
        Scanner s = new Scanner(pIs);
        List<Sentence> result = parse(s);
        s.close();
        return result;
    }

    public static List<Sentence> parse(String pInputString){
        List<Sentence> sentences = new ArrayList<>();
        Matcher sentenceMatcher = SENTENCE_PATTERN.matcher(pInputString);
        while(sentenceMatcher.find()){
            String stringSentence = sentenceMatcher.group().trim().
                    replaceAll(REPLACE_SPACE_SEQ, " ").replaceAll(REPLACE_TAB_SEQ, "\t");
            sentences.add(new Sentence(WordParser.parse(stringSentence), stringSentence));
        }
        return sentences;
    }

    public static List<Sentence> parse(Scanner pSc){
        pSc.useDelimiter("\\A");
        String result = pSc.hasNext() ? pSc.next() : "";
        return parse(result);
    }


    public static List<Sentence> parse(URL pUrl) throws TextParseException {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(pUrl.openStream()));
            String inputLine;
            StringBuilder strb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                strb.append(inputLine);
            }
            in.close();
            return parse(Jsoup.parse(strb.toString()).text());
        }
        catch (IOException e){
            throw new TextParseException(e);
        }
    }

    public static List<Sentence> parse(File pFile) throws TextParseException {
        try {
            InputStream inputStream = new FileInputStream(pFile);
            List<Sentence> result = parse(inputStream);
            inputStream.close();
            return result;
        }
        catch (IOException e){
            throw new TextParseException(e);
        }
    }

}
