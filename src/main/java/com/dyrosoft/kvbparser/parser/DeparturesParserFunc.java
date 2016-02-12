package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.models.Departure;
import com.dyrosoft.kvbparser.models.Line;
import com.dyrosoft.kvbparser.utils.StringUtils;
import com.google.common.collect.ImmutableList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DeparturesParserFunc extends AbstractRxHtmlParserFunc<ImmutableList<Departure>> {

    @Override
    protected ImmutableList<Departure> parse(final String html) {
        final ImmutableList.Builder<Departure> builder = ImmutableList.builder();

        final Document document = Jsoup.parse(html);
        final Elements elements = document.select("table.qr_table");
        for (final Element tr : elements.get(1).getElementsByTag("tr")) {
            final Elements tds = tr.getElementsByTag("td");
            final Departure departure =
                    new Departure(new Line(StringUtils.advacedTrim(tds.get(0).text())),
                                  StringUtils.advacedTrim(tds.get(1).text()),
                                  StringUtils.advacedTrim(tds.get(2).text()));
            builder.add(departure);
        }
        return builder.build();
    }
}
