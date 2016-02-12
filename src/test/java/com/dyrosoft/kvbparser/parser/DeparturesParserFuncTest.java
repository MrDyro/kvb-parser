package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.models.Departure;
import com.dyrosoft.kvbparser.models.Line;
import com.google.common.collect.ImmutableList;

import org.junit.Test;

import rx.Single;
import rx.functions.Action1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeparturesParserFuncTest {

    public DeparturesParserFuncTest() {
        super();
    }

    @Test
    public void testDeparturesParsingCount() throws Exception {
        Single.just(TestPages.DEPARTURES)
                .flatMap(new DeparturesParserFunc())
                .subscribe(new Action1<ImmutableList<Departure>>() {
                    @Override
                    public void call(final ImmutableList<Departure> departures) {
                        assertThat(departures.size(), is(20));
                    }
                });
    }

    @Test
    public void testDeparturesParsingObjects() throws Exception {
        Single.just(TestPages.DEPARTURES)
                .flatMap(new DeparturesParserFunc())
                .subscribe(new Action1<ImmutableList<Departure>>() {
                    @Override
                    public void call(final ImmutableList<Departure> departures) {
                        final Departure departure = departures.get(0);
                        assertThat(departure.getDirection(), is("Fahrt endet hier"));
                        assertThat(departure.getWaitTime(), is("3 Min"));

                        final Line line = departure.getLine();
                        assertThat(line.getId(), is("E"));
                    }
                });
    }
}