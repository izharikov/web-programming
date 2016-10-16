package com.string.utils;

import com.string.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordUtils {
    public static int countChars(Word pWord, char c){
        Pattern p = Pattern.compile("[" + c + "]{1}");
        Matcher m = p.matcher(pWord.getString());
        int result = 0;
        while (m.find()){
            ++result;
        }
        return result;
    }
}
