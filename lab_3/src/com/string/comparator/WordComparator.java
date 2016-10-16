package com.string.comparator;

import com.string.Word;

import java.util.Comparator;

/**
 * @author Ihar Zharykau
 */
public interface WordComparator extends Comparator<Word> {
    char getCharToCompare();
}
