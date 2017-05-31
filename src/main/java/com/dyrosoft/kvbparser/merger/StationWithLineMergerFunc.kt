package com.dyrosoft.kvbparser.merger

import com.dyrosoft.kvbparser.models.Line
import com.dyrosoft.kvbparser.models.Station
import com.dyrosoft.kvbparser.models.StationDetails

import rx.functions.Func2

internal class StationWithLineMergerFunc : Func2<List<Line>, Station, StationDetails> {

    override fun call(lines: List<Line>, station: Station) = StationDetails(station.id, station.name, lines)
}
