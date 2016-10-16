package com.string.utils;

import com.string.Sentence;
import com.string.info.WordInfo;
import com.string.Word;

import java.util.Set;


/**
 * @author Ihar Zharykau
 */
public class SentenceUtils {
    public static WordInfo countWords(Sentence pSentence, Set<Word> pWordSet){
        WordInfo result = new WordInfo(pWordSet);
        for(Word word : pSentence.getWords()){
            if ( pWordSet.contains(word)) {
                result.addWord(word);
            }
        }
        return result;
    }

    public static WordInfo countWords(WordInfo pAllWordInfo, Sentence pSentence, Set<Word> pWordSet){
        WordInfo result = new WordInfo(pWordSet);
        for(Word word : pSentence.getWords()){
            if ( pWordSet.contains(word)) {
                pAllWordInfo.addWord(word);
                result.addWord(word);
            }
        }
        return result;
    }

}
