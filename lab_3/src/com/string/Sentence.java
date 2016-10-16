package com.string;

import java.util.List;

/**
 * @author Ihar Zharykau
 */
public class Sentence {
    private List<Word> mWords;

    public List<Word> getWords() {
        return mWords;
    }

    public void setWords(List<Word> pWords) {
        mWords = pWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence = (Sentence) o;

        return mWords != null ? mWords.equals(sentence.mWords) : sentence.mWords == null;

    }

    @Override
    public int hashCode() {
        return mWords != null ? mWords.hashCode() : 0;
    }
}
