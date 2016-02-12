package com.dyrosoft.kvbparser.merger;

import com.dyrosoft.kvbparser.models.Departure;
import com.dyrosoft.kvbparser.models.Departures;
import com.dyrosoft.kvbparser.parser.DepartureInformationParserFunc;
import com.dyrosoft.kvbparser.parser.DeparturesParserFunc;
import com.google.common.collect.ImmutableList;

import rx.Single;
import rx.functions.Func2;

public class DeparturesMergerTransformer implements Single.Transformer<String, Departures> {

    @Override
    public Single<Departures> call(final Single<String> htmlSingle) {
        return Single.zip(htmlSingle.flatMap(new DeparturesParserFunc()),
                          htmlSingle.flatMap(new DepartureInformationParserFunc()),
                          new Func2<ImmutableList<Departure>, ImmutableList<String>, Departures>() {
                              @Override
                              public Departures call(final ImmutableList<Departure> departures,
                                                     final ImmutableList<String> departureInformation) {

                                  return new Departures(departures, departureInformation);
                              }
                          });
    }
}
