package com.dyrosoft.kvbparser.models

class StationDetails(id: String, name: String, val lines: List<Line>) : Station(id, name)
