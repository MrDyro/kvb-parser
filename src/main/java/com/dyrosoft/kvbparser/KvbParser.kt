package com.dyrosoft.kvbparser

import com.dyrosoft.kvbparser.api.ApiProvider
import com.dyrosoft.kvbparser.api.KvbApi
import com.dyrosoft.kvbparser.merger.DeparturesMergerTransformer
import com.dyrosoft.kvbparser.merger.StationWithLineMergerFunc
import com.dyrosoft.kvbparser.models.Departures
import com.dyrosoft.kvbparser.models.Line
import com.dyrosoft.kvbparser.models.Station
import com.dyrosoft.kvbparser.models.StationDetails
import com.dyrosoft.kvbparser.parser.StationLinesParserFuc
import com.dyrosoft.kvbparser.parser.StationsParserFuc
import com.dyrosoft.kvbparser.rx.ResponseFunc
import rx.Single
import rx.schedulers.Schedulers

class KvbParser {

    private val api: KvbApi = ApiProvider.kvbApi
    private val responseFunc: ResponseFunc = ResponseFunc()

    fun fetchAllStations(): Single<List<Station>> {
        return api.queryStations()
                .flatMap(responseFunc)
                .flatMap(StationsParserFuc())
                .subscribeOn(Schedulers.io())
    }

    fun fetchDepartures(station: Station): Single<Departures> {
        return api.queryDepartures(station.id)
                .flatMap(responseFunc)
                .compose(DeparturesMergerTransformer())
                .subscribeOn(Schedulers.io())
    }

    fun fetchStationDetail(station: Station): Single<StationDetails> {
        return Single.zip<List<Line>, Station, StationDetails>(api.queryStationDetail(station.id)
                                                                       .flatMap(responseFunc)
                                                                       .flatMap<List<Line>>(StationLinesParserFuc()),
                                                               Single.just(station),
                                                               StationWithLineMergerFunc())
                .subscribeOn(Schedulers.io())
    }
}
