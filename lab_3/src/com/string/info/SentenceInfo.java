package com.string.info;

import com.string.Word;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Ihar Zharykau
 */
public class SentenceInfo extends HashMap<Word, Integer> {
    public SentenceInfo(Set<Word> pWords) {
        for(Word word : pWords){
            put(word, 0);
        }
    }

    public void addWord(Word pWord){
        int current = get(pWord);
        put(pWord, current + 1);
    }
}
