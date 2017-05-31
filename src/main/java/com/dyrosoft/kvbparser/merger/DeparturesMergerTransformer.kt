package com.dyrosoft.kvbparser.merger

import com.dyrosoft.kvbparser.models.Departures
import com.dyrosoft.kvbparser.parser.DepartureInformationParserFunc
import com.dyrosoft.kvbparser.parser.DeparturesParserFunc
import rx.Single

internal class DeparturesMergerTransformer : Single.Transformer<String, Departures> {

    override fun call(htmlSingle: Single<String>): Single<Departures> {
        return Single.zip(htmlSingle.flatMap(DeparturesParserFunc()),
                          htmlSingle.flatMap(DepartureInformationParserFunc())
                         ) { departures, departureInformation -> Departures(departures, departureInformation) }
    }
}
