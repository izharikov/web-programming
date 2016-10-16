package com.string.info;

import com.string.Word;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ihar Zharykau
 */
public class WordInfo extends LinkedHashMap<Word, Integer> {
    private Set<Word> mWords;
    public WordInfo(Set<Word> pWords) {
        mWords = pWords;
        for(Word word : pWords){
            put(word, 0);
        }
    }

    public void addWord(Word pWord){
        int current = get(pWord);
        remove(pWord);
        put(pWord, current + 1);
    }

    public void sortByValue(){
        LinkedHashMap<Word, Integer> temp = this.entrySet().stream().
                sorted(reversedCompareByValue()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        clear();
        putAll(temp);
    }

    public static Comparator<Map.Entry<Word, Integer>> reversedCompareByValue(){
        return (Comparator<Map.Entry<Word, Integer>> & Serializable)
                (c1, c2) -> c2.getValue().compareTo(c1.getValue());
    }

    public Set<Word> getWords() {
        return mWords;
    }

    public void setWords(Set<Word> pWords) {
        mWords = pWords;
    }
}
