package com.dyrosoft.kvbparser.models;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class Departures {

    private ImmutableList<String> departureInformation;
    private ImmutableList<Departure> departures;

    public Departures(final List<Departure> departures,
                      final List<String> departureInformation) {

        this.departures = ImmutableList.copyOf(departures);
        this.departureInformation = ImmutableList.copyOf(departureInformation);
    }

    public ImmutableList<String> getDepartureInformation() {
        return departureInformation;
    }

    public ImmutableList<Departure> getDepartures() {
        return departures;
    }
}
