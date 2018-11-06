package com.github.jonpereiradev.integrator.client.configuration.stub;

public interface RequestMappingStub {

    void notEndpoint();
    
    void without();

    void withPath();

    void withPaths();

    void withValue();

    void withValues();
}
