package com.dyrosoft.kvbparser.models;

public class Station {

    private String id;
    private String name;

    public Station(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
