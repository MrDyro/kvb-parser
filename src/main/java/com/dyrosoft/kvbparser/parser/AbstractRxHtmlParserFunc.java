package com.dyrosoft.kvbparser.parser;

import rx.Single;
import rx.functions.Func1;

abstract class AbstractRxHtmlParserFunc<T> implements Func1<String, Single<T>> {

    @Override
    public Single<T> call(final String html) {
        return Single.just(parse(html));
    }

    protected abstract T parse(final String html);
}
