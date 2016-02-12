package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.utils.StringUtils;
import com.google.common.collect.ImmutableList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DepartureInformationParserFunc
        extends AbstractRxHtmlParserFunc<ImmutableList<String>> {

    @Override
    protected ImmutableList<String> parse(final String html) {
        final ImmutableList.Builder<String> builder = ImmutableList.builder();

        final Document document = Jsoup.parse(html);
        final Elements elements = document.select("table.qr_table");
        for (final Element tr : elements.get(0).getElementsByTag("tr")) {
            final Elements tds = tr.getElementsByTag("td");
            builder.add(StringUtils.advancedTrim(tds.get(0).text()));
        }
        return builder.build();
    }
}
