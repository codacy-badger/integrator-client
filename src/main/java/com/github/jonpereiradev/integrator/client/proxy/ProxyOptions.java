package com.github.jonpereiradev.integrator.client.proxy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public final class ProxyOptions {

    private static final Collection<String> IGNORE_HEADERS = Arrays.asList(
        HttpHeaders.CONTENT_LENGTH.toLowerCase(),
        HttpHeaders.HOST.toLowerCase()
    );

    static final ProxyOptions DEFAULT_OPTIONS = newJsonInstance();

    private final List<NameValueParam> headers = new ArrayList<>();
    private final List<NameValueParam> paths = new ArrayList<>();
    private final List<NameValueParam> queries = new ArrayList<>();

    private ProxyOptions() {
    }

    public static ProxyOptions newInstance() {
        return new ProxyOptions();
    }

    public static ProxyOptions newJsonInstance() {
        return new ProxyOptions().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    public static ProxyOptions newInstance(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        ProxyOptions proxyOptions = new ProxyOptions();

        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement().toString();

            if (!IGNORE_HEADERS.contains(nextElement.toLowerCase())) {
                proxyOptions.header(nextElement, request.getHeader(nextElement));
            }
        }

        return proxyOptions;
    }

    public ProxyOptions header(String name, String value) {
        headers.add(new NameValueParam(name, value));
        return this;
    }

    public ProxyOptions header(String name, String[] values) {
        for (String value : values) {
            header(name, value);
        }

        return this;
    }

    public ProxyOptions path(String name, Object value) {
        paths.add(new NameValueParam(name, value.toString()));
        return this;
    }

    public ProxyOptions query(String name, String value) {
        queries.add(new NameValueParam(name, value));
        return this;
    }

    List<NameValueParam> getHeaders() {
        return Collections.unmodifiableList(headers);
    }

    List<NameValueParam> getPaths() {
        return Collections.unmodifiableList(paths);
    }

    List<NameValueParam> getQueries() {
        return Collections.unmodifiableList(queries);
    }
}
