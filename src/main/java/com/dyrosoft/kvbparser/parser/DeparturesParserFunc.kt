package com.dyrosoft.kvbparser.parser

import com.dyrosoft.kvbparser.models.Departure
import com.dyrosoft.kvbparser.models.Line
import com.dyrosoft.kvbparser.utils.StringUtils
import io.multifunctions.letNotNull
import org.jsoup.Jsoup

internal class DeparturesParserFunc : AbstractRxHtmlParserFunc<List<Departure>>() {

    override fun parse(html: String): List<Departure> {
        val list = mutableListOf<Departure>()
        val document = Jsoup.parse(html)
        val elements = document.select("table.qr_table")
        elements[1].getElementsByTag("tr")
                .map { it.getElementsByTag("td") }
                .forEach {
                    Triple(StringUtils.advancedTrim(it[0].text()),
                           StringUtils.advancedTrim(it[1].text()),
                           StringUtils.advancedTrim(it[2].text())).letNotNull { line, direction, waitTime ->

                        list.add(Departure(Line(line), direction, waitTime))
                    }
                }
        return list.toList()
    }
}
