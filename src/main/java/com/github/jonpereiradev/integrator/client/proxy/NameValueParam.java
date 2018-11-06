package com.github.jonpereiradev.integrator.client.proxy;

final class NameValueParam {

    private final String name;
    private final String value;

    NameValueParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    String getName() {
        return name;
    }

    String getValue() {
        return value;
    }
}
