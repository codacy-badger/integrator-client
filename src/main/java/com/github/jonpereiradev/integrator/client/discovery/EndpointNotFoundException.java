package com.github.jonpereiradev.integrator.client.discovery;

public class EndpointNotFoundException extends RuntimeException {
    
    public EndpointNotFoundException() {
        super();
    }

    public EndpointNotFoundException(String message) {
        super(message);
    }

    public EndpointNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EndpointNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EndpointNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
