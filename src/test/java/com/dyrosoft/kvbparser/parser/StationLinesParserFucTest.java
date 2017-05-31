package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.TestUtils;

import org.junit.Test;

import rx.Single;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StationLinesParserFucTest {

    @Test
    public void testStationLinesParsingCount() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.STATION_LINES))
                .flatMap(new StationLinesParserFuc())
                .subscribe(lines -> assertThat(lines.size(), is(7)));
    }

    @Test
    public void testStationLinesParsingObjects() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.STATION_LINES))
                .flatMap(new StationLinesParserFuc())
                .subscribe(lines -> {
                    assertThat(lines.get(0).getId(), is("1"));
                    assertThat(lines.get(6).getId(), is("133"));
                });
    }
}