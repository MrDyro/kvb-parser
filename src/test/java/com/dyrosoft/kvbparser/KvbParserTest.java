package com.dyrosoft.kvbparser;

import com.dyrosoft.kvbparser.models.Departures;
import com.dyrosoft.kvbparser.models.Station;
import com.dyrosoft.kvbparser.models.StationDetails;
import com.google.common.collect.ImmutableList;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class KvbParserTest {

    @Test
    public void testFetchAllStations() throws Exception {
        final KvbParser parser = new KvbParser();

        final ImmutableList<Station> stations = parser.fetchAllStations().toBlocking().value();

        assertThat(stations.isEmpty(), is(not(true)));
    }

    @Test
    public void testFetchDepartures() throws Exception {
        final KvbParser parser = new KvbParser();
        final Station station = new Station("1", "Heumarkt");

        final Departures departures = parser.fetchDepartures(station).toBlocking().value();

        assertThat(departures, is(notNullValue()));
        assertThat(departures.getDepartureInformation().isEmpty(), is(not(true)));
        assertThat(departures.getDepartures().isEmpty(), is(not(true)));
    }

    @Test
    public void testFetchStationDetail() throws Exception {
        final KvbParser parser = new KvbParser();
        final Station station = new Station("1", "Heumarkt");

        final StationDetails stationDetails =
                parser.fetchStationDetail(station).toBlocking().value();

        assertThat(stationDetails, is(notNullValue()));
        assertThat(stationDetails.getLines().isEmpty(), is(not(true)));
    }
}