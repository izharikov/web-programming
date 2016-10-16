package com.common;

import java.util.*;

/**
 * @author Ihar Zharykau
 */
public class CommonUtils {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> pUnsortedMap) {

        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(pUnsortedMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;

    }
}
