package com.constants;

import java.util.regex.Pattern;

/**
 * @author Ihar Zharykau
 */
public interface Constants {
    Pattern SENTENCE_PATTERN = Pattern.compile("[^.!?]+");
    Pattern WORD_PATTERN = Pattern.compile("([\\$]{0,1}[\\w\\p{IsCyrillic}]+['-]{0,1})+");
    String REPLACE_SPACE_SEQ = "[ ]+";
    String REPLACE_TAB_SEQ = "[\t]+";
}
