package com.internship.dataCollector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class ScrapperSettings {

    protected static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/45.0.2454.101 Safari/537.36";
    protected static final String REFERRER = "http://www.google.com";
    protected static final String PROXY_HOST = "webproxy.stat.intra";
    protected static final int PROXY_PORT = 8080;
    protected static final int TIMEOUT = 10 * 1000;

    protected final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    protected Document connectWith(String link) throws IOException {
        Document document = Jsoup.connect(link)
                .proxy(PROXY_HOST, PROXY_PORT)
                .userAgent(USER_AGENT)
                .referrer(REFERRER)
                .timeout(TIMEOUT)
                .ignoreHttpErrors(true)
                .followRedirects(true)
                .get();
        return document;
    }
}
