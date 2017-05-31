package com.dyrosoft.kvbparser.parser

import com.dyrosoft.kvbparser.utils.StringUtils
import org.jsoup.Jsoup

internal class DepartureInformationParserFunc : AbstractRxHtmlParserFunc<List<String>>() {

    override fun parse(html: String): List<String> {
        val list = mutableListOf<String>()
        val document = Jsoup.parse(html)
        val elements = document.select("table.qr_table")
        elements[0].getElementsByTag("tr")
                .map { it.getElementsByTag("td") }
                .forEach {
                    StringUtils.advancedTrim(it[0].text())?.run {
                        list.add(this)
                    }
                }
        return list.toList()
    }
}
