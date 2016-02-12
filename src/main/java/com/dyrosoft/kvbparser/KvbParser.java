package com.dyrosoft.kvbparser;

import com.dyrosoft.kvbparser.api.ApiProvider;
import com.dyrosoft.kvbparser.api.KvbApi;
import com.dyrosoft.kvbparser.merger.DeparturesMergerTransformer;
import com.dyrosoft.kvbparser.merger.StationWithLineMergerFunc;
import com.dyrosoft.kvbparser.models.Departures;
import com.dyrosoft.kvbparser.models.Station;
import com.dyrosoft.kvbparser.models.StationDetails;
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

    public Single<ImmutableList<Station>> fetchAllStations() {
        return api.queryStations()
                .flatMap(responseFunc)
                .flatMap(new StationsParserFuc())
                .subscribeOn(Schedulers.io());
    }

    public Single<Departures> fetchDepartures(final Station station) {
        return api.queryDepartures(station.getId())
                .flatMap(responseFunc)
                .compose(new DeparturesMergerTransformer())
                .subscribeOn(Schedulers.io());
    }

    public Single<StationDetails> fetchStationDetail(final Station station) {
        return Single.zip(api.queryStationDetail(station.getId())
                                  .flatMap(responseFunc)
                                  .flatMap(new StationLinesParserFuc()),
                          Single.just(station),
                          new StationWithLineMergerFunc())
                .subscribeOn(Schedulers.io());
    }
}
