package com.github.jonpereiradev.integrator.client.configuration;

import com.github.jonpereiradev.integrator.client.model.Resource;
import com.github.jonpereiradev.integrator.client.model.UserAuth;
import com.github.jonpereiradev.integrator.client.discovery.DiscoveryEndpoint;
import com.github.jonpereiradev.integrator.client.proxy.ProxyEndpoint;
import com.github.jonpereiradev.integrator.client.proxy.ProxyOptions;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

public class AuthClientConfiguration extends WebSecurityConfigurerAdapter {

    protected final ProxyEndpoint proxyEndpoint;
    protected final DiscoveryEndpoint discoveryEndpoint;

    public AuthClientConfiguration(ProxyEndpoint proxyEndpoint, DiscoveryEndpoint discoveryEndpoint) {
        this.proxyEndpoint = proxyEndpoint;
        this.discoveryEndpoint = discoveryEndpoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((String username) -> {
            ProxyOptions proxyOptions = ProxyOptions.newInstance().path("username", username);
            Resource resource = discoveryEndpoint.discovery("auth-load-user-by-username");
            GetRequest getRequest = proxyEndpoint.newGetRequest(resource.getPath(), proxyOptions);
            HttpResponse<JsonNode> response = proxyEndpoint.asJson(getRequest);

            if (HttpStatus.valueOf(response.getStatus()) == HttpStatus.OK && response.getBody() != null) {
                JSONObject body = response.getBody().getObject();
                return new UserAuth(body.getString("username"), body.getString("password"));
            }

            return null;
        }).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
