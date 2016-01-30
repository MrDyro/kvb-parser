package com.dyrosoft.kvbparser;

import com.dyrosoft.kvbparser.models.Departure;
import com.dyrosoft.kvbparser.models.Line;
import com.google.common.collect.ImmutableList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import rx.Single;
import rx.functions.Func1;

class DeparturesParserFunc implements Func1<String, Single<ImmutableList<Departure>>> {
    @Override
    public Single<ImmutableList<Departure>> call(final String html) {
        final ImmutableList.Builder<Departure> builder = ImmutableList.builder();

        final Document document = Jsoup.parse(html);
        final Elements elements = document.select("table.qr_table");
        for (final Element tr : elements.get(1).getElementsByTag("tr")) {
            final Elements tds = tr.getElementsByTag("td");
            final Departure departure =
                    new Departure(new Line(tds.get(0).text().trim()),
                                  tds.get(1).text().trim(),
                                  tds.get(2).text().trim());
            builder.add(departure);
        }

        return Single.just(builder.build());
    }
}
