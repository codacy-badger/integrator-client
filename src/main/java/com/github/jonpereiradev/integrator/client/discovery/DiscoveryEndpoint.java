package com.github.jonpereiradev.integrator.client.discovery;

import com.github.jonpereiradev.integrator.client.cache.Cacheable;
import com.github.jonpereiradev.integrator.client.configuration.IntegratorEnvironment;
import com.github.jonpereiradev.integrator.client.model.Resource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Lazy
@Primary
@Component
public class DiscoveryEndpoint {

    private final Cacheable cacheable;
    private final RestTemplate restTemplate;
    private final IntegratorEnvironment integratorEnvironment;

    @Autowired
    public DiscoveryEndpoint(Cacheable cacheable, RestTemplate restTemplate, IntegratorEnvironment integratorEnvironment) {
        this.cacheable = cacheable;
        this.restTemplate = restTemplate;
        this.integratorEnvironment = integratorEnvironment;
    }

    public Resource discovery(String endpoint) {
        return (Resource) cacheable.execute(Resource.class, endpoint, () -> {
            String address = integratorEnvironment.getIntegratorAddress();
            ResponseEntity<String> forEntity = restTemplate.getForEntity(address + "/applications/resources/{identifier}", String.class, endpoint);

            if (forEntity.getBody() == null) {
                throw new EndpointNotFoundException(String.format("Endpoint not found with name '%s'", endpoint));
            }

            JSONObject json = new JSONObject(forEntity.getBody());
            String identifier = json.getString("identifier");
            String application = json.getString("application");
            String path = json.getString("path");

            return new Resource(identifier, "/proxy/" + application + path);
        });
    }

}
