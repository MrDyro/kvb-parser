package com.dyrosoft.kvbparser.models;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LineTest {

    @Test
    public void testGetType() throws Exception {
        final Line line1 = new Line("E");
        assertThat(line1.getType(), is(Line.LineType.SPECIAL));

        final Line line2 = new Line("4");
        assertThat(line2.getType(), is(Line.LineType.TRAM));

        final Line line3 = new Line("155");
        assertThat(line3.getType(), is(Line.LineType.BUS));
    }
}