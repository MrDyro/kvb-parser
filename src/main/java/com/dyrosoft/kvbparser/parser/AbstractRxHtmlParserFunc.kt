package com.dyrosoft.kvbparser.parser

import rx.Single
import rx.functions.Func1

abstract class AbstractRxHtmlParserFunc<T> : Func1<String, Single<T>> {

    override fun call(html: String): Single<T> = Single.just(parse(html))

    protected abstract fun parse(html: String): T
}
