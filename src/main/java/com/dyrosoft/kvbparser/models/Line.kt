package com.dyrosoft.kvbparser.models


data class Line(val id: String) {

    enum class LineType {
        TRAM, BUS, SPECIAL
    }

    val type: LineType
        get() {
            id.toIntOrNull()?.let {
                if (it >= 100) {
                    return LineType.BUS
                } else {
                    return LineType.TRAM
                }
            } ?: return LineType.SPECIAL
        }
}
