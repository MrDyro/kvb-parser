package com.dyrosoft.kvbparser.parser

import com.dyrosoft.kvbparser.models.Station
import org.jsoup.Jsoup
import java.util.regex.Pattern

internal class StationsParserFuc : AbstractRxHtmlParserFunc<List<Station>>() {

    override fun parse(html: String): List<Station> {
        val list = mutableListOf<Station>()
        val document = Jsoup.parse(html)
        val elements = document.getElementsByTag("a")
        for (element in elements) {
            val href = element.attr("href")
            if (href.isNullOrEmpty()) {
                continue
            }

            val pattern = Pattern.compile("/german/hst/overview/(\\d+)/")
            val matcher = pattern.matcher(href)
            if (!matcher.matches()) {
                continue
            }

            val id = href.replace("german/hst/overview/", "").replace("/", "")

            list.add(Station(id, element.text()))
        }

        return list.toList()
    }
}
