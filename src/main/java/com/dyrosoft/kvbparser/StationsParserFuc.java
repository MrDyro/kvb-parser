package com.dyrosoft.kvbparser;

import com.dyrosoft.kvbparser.models.Station;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Single;
import rx.functions.Func1;

class StationsParserFuc implements Func1<String, Single<ImmutableList<Station>>> {

    @Override
    public Single<ImmutableList<Station>> call(final String html) {
        final ImmutableList.Builder<Station> builder = ImmutableList.builder();

        final Document document = Jsoup.parse(html);
        final Elements elements = document.getElementsByTag("a");
        for (final Element element : elements) {
            final String href = element.attr("href");
            if (Strings.isNullOrEmpty(href)) {
                continue;
            }

            final Pattern pattern = Pattern.compile("/german/hst/overview/(\\d+)/");
            final Matcher matcher = pattern.matcher(href);
            if (!matcher.matches()) {
                continue;
            }

            final String id =
                    href.replace("german/hst/overview/", "").replace("/", "");

            builder.add(new Station(id, element.text()));
        }

        return Single.just(builder.build());
    }
}
