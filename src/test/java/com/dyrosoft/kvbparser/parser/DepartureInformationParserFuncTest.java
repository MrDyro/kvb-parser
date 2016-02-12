package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.TestUtils;
import com.google.common.collect.ImmutableList;

import org.junit.Test;

import rx.Single;
import rx.functions.Action1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DepartureInformationParserFuncTest {

    @Test
    public void testDepartureInformationParsing() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.DEPARTURES))
                .flatMap(new DepartureInformationParserFunc())
                .subscribe(new Action1<ImmutableList<String>>() {
                    @Override
                    public void call(final ImmutableList<String> info) {
                        assertThat(info.size(), is(1));
                    }
                });
    }
}