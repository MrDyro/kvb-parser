package com.dyrosoft.kvbparser.parser

import com.dyrosoft.kvbparser.models.Line
import org.jsoup.Jsoup
import java.util.regex.Pattern

internal class StationLinesParserFuc : AbstractRxHtmlParserFunc<List<Line>>() {

    override fun parse(html: String): List<Line> {
        val list = mutableListOf<Line>()
        val document = Jsoup.parse(html)
        val elements = document.getElementsByTag("a")

        for (element in elements) {
            val href = element.attr("href")
            if (href.isNullOrEmpty()) {
                continue
            }

            val pattern = Pattern.compile("/german/hst/showline/(\\d+)/(\\d+)/")
            val matcher = pattern.matcher(href)
            if (!matcher.matches()) {
                continue
            }

            val temp = href.replace("/german/hst/showline/", "")
            val id = temp.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

            list.add(Line(id))
        }

        return list.toList()
    }
}
