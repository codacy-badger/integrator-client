package com.github.jonpereiradev.integrator.client.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Resource implements Serializable {

    private String identifier;
    private String path;

    public Resource() {
    }

    public Resource(String identifier, String path) {
        this.identifier = identifier;
        this.path = path;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPath() {
        return path;
    }

}
