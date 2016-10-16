package com.constants;

import java.util.regex.Pattern;

/**
 * @author Ihar Zharykau
 */
public interface Constants {
    Pattern SENTENCE_PATTERN = Pattern.compile("[A-Za-z\\d': ,;-]+");
    Pattern WORD_PATTERN = Pattern.compile("([\\w]+['-]{0,1})+");
}
