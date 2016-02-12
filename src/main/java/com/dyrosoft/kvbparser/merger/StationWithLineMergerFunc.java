package com.dyrosoft.kvbparser.merger;

import com.dyrosoft.kvbparser.models.Line;
import com.dyrosoft.kvbparser.models.Station;
import com.dyrosoft.kvbparser.models.StationDetails;

import java.util.List;

import rx.functions.Func2;

public class StationWithLineMergerFunc
        implements Func2<List<Line>, Station, StationDetails> {

    @Override
    public StationDetails call(final List<Line> lines, final Station station) {
        return new StationDetails(station.getId(), station.getName(), lines);
    }
}
