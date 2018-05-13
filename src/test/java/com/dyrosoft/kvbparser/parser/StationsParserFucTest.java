package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.TestUtils;
import com.dyrosoft.kvbparser.models.Station;

import org.junit.Test;

import rx.Single;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StationsParserFucTest {

    @Test
    public void testStationLinesParsingCount() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.STATIONS))
                .flatMap(new StationsParserFuc())
                .subscribe(stations -> assertThat(stations.size(),
                                                  is(75)));
    }

    @Test
    public void testStationsParsingObjects() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.STATIONS))
                .flatMap(new StationsParserFuc())
                .subscribe(stations -> {
                    final Station station1 = stations.get(0);
                    assertThat(station1.getId(), is("702"));
                    assertThat(station1.getName(), is("Weiden West"));

                    final Station station2 = stations.get(33);
                    assertThat(station2.getId(), is("667"));
                    assertThat(station2.getName(), is("Neuenweg"));
                });
    }
}