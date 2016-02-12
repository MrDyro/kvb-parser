package com.dyrosoft.kvbparser;

import com.dyrosoft.kvbparser.api.ApiProvider;
import com.dyrosoft.kvbparser.api.KvbApi;
import com.dyrosoft.kvbparser.models.Departure;
import com.dyrosoft.kvbparser.models.Station;
import com.dyrosoft.kvbparser.models.StationDetails;
import com.dyrosoft.kvbparser.parser.DeparturesParserFunc;
import com.dyrosoft.kvbparser.parser.StationLinesParserFuc;
import com.dyrosoft.kvbparser.parser.StationsParserFuc;
import com.dyrosoft.kvbparser.rx.ResponseFunc;
import com.google.common.collect.ImmutableList;

import rx.Single;
import rx.schedulers.Schedulers;

public class KvbParser {

    private final KvbApi api;
    private final ResponseFunc responseFunc;

    public KvbParser() {
        api = ApiProvider.getKvbApi();
        responseFunc = new ResponseFunc();
    }

    public Single<ImmutableList<Station>> getAllStations() {
        return api.queryStations()
                .flatMap(responseFunc)
                .flatMap(new StationsParserFuc())
                .subscribeOn(Schedulers.io());
    }

    public Single<ImmutableList<Departure>> getDepartures(final Station station) {
        return api.queryDepartures(station.getId())
                .flatMap(responseFunc)
                .flatMap(new DeparturesParserFunc())
                .subscribeOn(Schedulers.io());
    }

    public Single<StationDetails> getStationDetail(final Station station) {
        return api.queryStationDetail(station.getId())
                .flatMap(responseFunc)
                .flatMap(new StationLinesParserFuc())
                .flatMap(new StationWithLineMergerFunc(station))
                .subscribeOn(Schedulers.io());
    }
}
