package com.github.jonpereiradev.integrator.client.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.jonpereiradev.integrator.client.model.Resource;
import com.github.jonpereiradev.integrator.client.discovery.DiscoveryEndpoint;
import com.github.jonpereiradev.integrator.client.proxy.ProxyEndpoint;
import com.github.jonpereiradev.integrator.client.proxy.ProxyOptions;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.GetRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public final class StatelessSecurityFilter extends AbstractStatelessSecurityFilter {

    private final ProxyEndpoint proxyEndpoint;
    private final DiscoveryEndpoint discoveryEndpoint;

    public StatelessSecurityFilter(ProxyEndpoint proxyEndpoint, DiscoveryEndpoint discoveryEndpoint) {
        this.proxyEndpoint = proxyEndpoint;
        this.discoveryEndpoint = discoveryEndpoint;
    }

    @Override
    public final void authenticate(DecodedJWT token) {
        Long id = token.getClaim("id").asLong();
        ProxyOptions proxyOptions = ProxyOptions.newJsonInstance().path("id", id);
        Resource resource = discoveryEndpoint.discovery("auth-find-user-by-id");
        GetRequest getRequest = proxyEndpoint.newGetRequest(resource.getPath(), proxyOptions);
        HttpResponse<JsonNode> response = proxyEndpoint.asJson(getRequest);

        if (HttpStatus.valueOf(response.getStatus()) == HttpStatus.OK && response.getBody() != null) {
            UsernamePasswordAuthenticationToken authentication;
            String username = response.getBody().getObject().getString("username");
            String password = response.getBody().getObject().getString("password");
            authentication = new UsernamePasswordAuthenticationToken(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}
