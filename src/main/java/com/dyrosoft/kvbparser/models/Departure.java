package com.dyrosoft.kvbparser.models;

public class Departure {

    private final String direction;
    private final Line line;
    private final String waitTime;

    public Departure(final Line line, final String direction, final String waitTime) {
        this.line = line;
        this.direction = direction;
        this.waitTime = waitTime;
    }

    public String getDirection() {
        return direction;
    }

    public Line getLine() {
        return line;
    }

    public String getWaitTime() {
        return waitTime;
    }
}
