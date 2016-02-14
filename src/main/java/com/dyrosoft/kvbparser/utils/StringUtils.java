package com.dyrosoft.kvbparser.utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String advancedTrim(final String toTrim) {
        if (toTrim == null) {
            return null;
        }

        return toTrim.replace(String.valueOf((char) 160), " ").trim();
    }
}
