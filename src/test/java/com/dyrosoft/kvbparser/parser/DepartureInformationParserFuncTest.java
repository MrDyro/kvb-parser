package com.dyrosoft.kvbparser.parser;

import com.dyrosoft.kvbparser.TestPages;
import com.dyrosoft.kvbparser.TestUtils;

import org.junit.Test;

import rx.Single;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DepartureInformationParserFuncTest {

    @Test
    public void testDepartureInformationParsing() throws Exception {
        Single.just(TestUtils.getTestHtmlFile(TestPages.DEPARTURES))
                .flatMap(new DepartureInformationParserFunc())
                .subscribe(info -> assertThat(info.size(), is(1)));
    }
}