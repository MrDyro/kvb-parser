package com.dyrosoft.kvbparser;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestUtils {

    private TestUtils() {
    }

    public static String getTestHtmlFile(final String htmlFile) throws IOException {
        final URL url =
                Thread.currentThread().getContextClassLoader().getResource("testfiles/" + htmlFile);

        if (url == null) {
            return "";
        }

        final File file = new File(url.getPath());
        return Files.toString(file, Charsets.UTF_8);
    }
}
