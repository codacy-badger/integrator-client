package com.github.jonpereiradev.integrator.client.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UnirestObjectMapper implements com.mashape.unirest.http.ObjectMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T readValue(String value, Class<T> valueType) {
        try {
            return mapper.readValue(value, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String writeValue(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
