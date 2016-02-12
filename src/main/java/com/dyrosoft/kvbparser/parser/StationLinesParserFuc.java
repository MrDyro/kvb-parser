package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.models.Line;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StationLinesParserFuc extends AbstractRxHtmlParserFunc<ImmutableList<Line>> {

    @Override
    protected ImmutableList<Line> parse(final String html) {
        final ImmutableList.Builder<Line> builder = ImmutableList.builder();

        final Document document = Jsoup.parse(html);
        final Elements elements = document.getElementsByTag("a");
        for (final Element element : elements) {
            final String href = element.attr("href");
            if (Strings.isNullOrEmpty(href)) {
                continue;
            }

            final Pattern pattern = Pattern.compile("/german/hst/showline/(\\d+)/(\\d+)/");
            final Matcher matcher = pattern.matcher(href);
            if (!matcher.matches()) {
                continue;
            }

            final String temp = href.replace("/german/hst/showline/", "");
            final String id = temp.split("/")[1];

            builder.add(new Line(id));
        }

        return builder.build();
    }
}
