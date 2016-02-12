package com.dyrosoft.kvbparser.utils;

public class StringUtils {

    public static String advacedTrim(final String toTrim) {
        if (toTrim == null) {
            return null;
        }

        return toTrim.replace(String.valueOf((char) 160), " ").trim();
    }
}
