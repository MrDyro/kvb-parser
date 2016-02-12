package com.dyrosoft.kvbparser.utils;

import com.google.common.base.Charsets;

public class StringUtils {

    private StringUtils() {
    }

    public static String advancedTrim(final String toTrim) {
        if (toTrim == null) {
            return null;
        }

        return toTrim.replace(String.valueOf((char) 160), " ").trim();
    }

    public static String convertToUtf8(final String string) {
        final byte[] isoBytes = string.getBytes(Charsets.ISO_8859_1);
        return new String(isoBytes, Charsets.UTF_8);
    }
}
