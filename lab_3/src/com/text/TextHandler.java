package com.text;

import com.common.CommonUtils;
import com.string.Sentence;
import com.string.Word;
import com.string.comparator.WordComparatorByChar;
import com.string.info.WordInfo;
import com.string.utils.SentenceUtils;

import java.util.*;

/**
 * @author Ihar Zharykau
 */
public class TextHandler {
    public void sortByChar(char c, List<Word> pWords) {
        if (pWords != null) {
            Collections.sort(pWords, new WordComparatorByChar().setCharToCompare(c));
        }
    }

    public Map<Sentence, WordInfo> sortByCountInText(WordInfo pWordInfo, List<Sentence> pSentences) {
        Map<Sentence, WordInfo> infoMap = new HashMap<>();
        for (Sentence sentence : pSentences) {
            infoMap.put(sentence, SentenceUtils.countWords(pWordInfo, sentence, pWordInfo.getWords()));
        }
        pWordInfo.sortByValue();
        return infoMap;
    }
}
