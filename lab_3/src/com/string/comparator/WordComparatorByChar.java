package com.string.comparator;

import com.string.Word;
import com.string.utils.WordUtils;

/**
 * @author Ihar Zharykau
 */
public class WordComparatorByChar implements WordComparator {
    private char c = 0;
    @Override
    public int compare(Word o1, Word o2) {
        int result = WordUtils.countChars(o1, getCharToCompare()) - WordUtils.countChars(o2, getCharToCompare());
        if ( result == 0){
            result = o1.compareTo(o2);
        }
        return result;
    }

    @Override
    public char getCharToCompare() {
        return c;
    }

    @Override
    public WordComparator setCharToCompare(char c) {
        this.c = c;
        return this;
    }
}
