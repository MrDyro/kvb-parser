package com.dyrosoft.kvbparser;

import com.dyrosoft.kvbparser.models.Line;
import com.dyrosoft.kvbparser.models.Station;
import com.dyrosoft.kvbparser.models.StationDetails;
import com.google.common.collect.ImmutableList;

import rx.Single;
import rx.functions.Func1;

class StationWithLineMergerFunc implements Func1<ImmutableList<Line>, Single<StationDetails>> {

    private final Station station;

    public StationWithLineMergerFunc(final Station station) {
        this.station = station;
    }

    @Override
    public Single<StationDetails> call(final ImmutableList<Line> lines) {
        return Single.just(new StationDetails(station.getId(), station.getName(), lines));
    }
}
