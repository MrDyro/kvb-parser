package com.dyrosoft.kvbparser.merger;

import com.dyrosoft.kvbparser.models.Line;
import com.dyrosoft.kvbparser.models.Station;
import com.dyrosoft.kvbparser.models.StationDetails;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Single;
import rx.functions.Action1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StationWithLineMergerFuncTest {

    @Test
    public void testMerging() throws Exception {
        final List<Line> lines = new ArrayList<>();
        lines.add(new Line("4"));
        lines.add(new Line("155"));

        final Station station = new Station("1", "Heumarkt");

        Single.zip(Single.just(lines), Single.just(station), new StationWithLineMergerFunc())
                .subscribe(new Action1<StationDetails>() {
                    @Override
                    public void call(final StationDetails stationDetails) {
                        assertThat(stationDetails.getLines().size(), is(2));
                        assertThat(stationDetails.getName(), is("Heumarkt"));
                    }
                });
    }
}