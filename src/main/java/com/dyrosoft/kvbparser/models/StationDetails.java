package com.dyrosoft.kvbparser.models;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class StationDetails extends Station {

    private final ImmutableList<Line> lines;

    public StationDetails(final String id, final String name, final List<Line> lines) {
        super(id, name);

        this.lines = ImmutableList.copyOf(lines);
    }

    public ImmutableList<Line> getLines() {
        return lines;
    }
}
