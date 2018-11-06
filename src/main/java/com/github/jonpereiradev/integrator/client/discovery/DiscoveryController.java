package com.github.jonpereiradev.integrator.client.discovery;

import com.github.jonpereiradev.integrator.client.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/discovery", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscoveryController {

    private final DiscoveryEndpoint discoveryEndpoint;

    @Autowired
    public DiscoveryController(DiscoveryEndpoint discoveryEndpoint) {
        this.discoveryEndpoint = discoveryEndpoint;
    }

    @GetMapping(path = "/{endpoint}")
    @ResponseStatus(HttpStatus.OK)
    public Resource findResourceByEndpoint(@PathVariable String endpoint) {
        return discoveryEndpoint.discovery(endpoint);
    }
}
