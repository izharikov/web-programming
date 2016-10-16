package com.string.utils;

import com.string.Sentence;
import com.string.info.SentenceInfo;
import com.string.Word;

import java.util.Set;


/**
 * @author Ihar Zharykau
 */
public class SentenceUtils {
    public static SentenceInfo countWords(Sentence pSentence, Set<Word> pWordSet){
        SentenceInfo result = new SentenceInfo(pWordSet);
        for(Word word : pSentence.getWords()){
            if ( pWordSet.contains(word)) {
                result.addWord(word);
            }
        }
        return result;
    }

    public static void countWords(SentenceInfo pSentenceInfo, Sentence pSentence, Set<Word> pWordSet){
        for(Word word : pSentence.getWords()){
            if ( pWordSet.contains(word)) {
                pSentenceInfo.addWord(word);
            }
        }
    }

}
