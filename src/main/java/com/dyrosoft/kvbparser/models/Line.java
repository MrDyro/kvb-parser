package com.dyrosoft.kvbparser.models;

import org.apache.commons.lang3.StringUtils;

public class Line {

    public enum LineType {
        TRAM, BUS, SPECIAL
    }

    private final String id;

    public Line(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LineType getType() {
        if (StringUtils.isNumeric(id)) {
            final int lineNumber = Integer.valueOf(id);
            if (lineNumber >= 100) {
                return LineType.BUS;
            } else {
                return LineType.TRAM;
            }
        } else {
            return LineType.SPECIAL;
        }
    }
}
