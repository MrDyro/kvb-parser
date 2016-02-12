package com.dyrosoft.kvbparser.merger;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.TestUtils;
import com.dyrosoft.kvbparser.models.Departures;

import org.junit.Test;

import rx.Single;
import rx.functions.Action1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeparturesTransformerTest {

    @Test
    public void testTransformation() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.DEPARTURES))
                .compose(new DeparturesMergerTransformer())
                .subscribe(new Action1<Departures>() {
                    @Override
                    public void call(final Departures departures) {
                        assertThat(departures.getDepartureInformation().size(), is(1));
                        assertThat(departures.getDepartures().size(), is(90));
                    }
                });
    }
}