package com.github.jonpereiradev.integrator.client.discovery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Endpoint {

    /**
     * Defines the endpoint name for registry and service discovery.
     *
     * @return the identification name of the resource on the registry.
     */
    String value();

}
