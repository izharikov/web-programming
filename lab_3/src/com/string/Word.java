package com.string;

/**
 * @author Ihar Zharykau
 */
public class Word implements Comparable<Word>{
    private String mString;

    public Word(String pString) {
        mString = pString;
    }

    public String getString() {
        return mString;
    }

    public void setString(String pString) {
        this.mString = pString;
    }

    @Override
    public int compareTo(Word o) {
        return getString().compareTo(o.getString());
    }

    @Override
    public String toString() {
        return mString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return mString != null ? mString.equals(word.mString) : word.mString == null;

    }

    @Override
    public int hashCode() {
        return mString != null ? mString.hashCode() : 0;
    }
}
