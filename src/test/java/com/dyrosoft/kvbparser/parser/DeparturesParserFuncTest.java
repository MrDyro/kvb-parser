package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.TestUtils;
import com.dyrosoft.kvbparser.models.Departure;
import com.dyrosoft.kvbparser.models.Line;

import org.junit.Test;

import rx.Single;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeparturesParserFuncTest {

    @Test
    public void testDeparturesParsingCount() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.DEPARTURES))
                .flatMap(new DeparturesParserFunc())
                .subscribe(departures -> assertThat(departures.size(), is(47)));
    }

    @Test
    public void testDeparturesParsingObjects() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.DEPARTURES))
                .flatMap(new DeparturesParserFunc())
                .subscribe(departures -> {
                    final Departure departure = departures.get(0);
                    assertThat(departure.getDirection(), is("Bensberg"));
                    assertThat(departure.getWaitTime(), is("Sofort"));

                    final Line line = departure.getLine();
                    assertThat(line.getId(), is("1"));

                    final Departure departure2 = departures.get(46);
                    assertThat(departure2.getDirection(), is("Bensberg"));
                    assertThat(departure2.getWaitTime(), is("59 Min"));

                    final Line line2 = departure2.getLine();
                    assertThat(line2.getId(), is("1"));
                });
    }
}