package com.github.jonpereiradev.integrator.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.jonpereiradev.integrator.client.configuration.IntegratorEnvironment;
import com.github.jonpereiradev.integrator.client.model.ApplicationRequest;
import com.github.jonpereiradev.integrator.client.ping.PingServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class Deployer {

    private static final Logger LOGGER = Logger.getLogger(Deployer.class.getName());

    private final IntegratorService integratorService;
    private final IntegratorEnvironment integratorEnvironment;

    @Autowired
    public Deployer(IntegratorService integratorService, IntegratorEnvironment integratorEnvironment) {
        this.integratorService = integratorService;
        this.integratorEnvironment = integratorEnvironment;
    }

    @PostConstruct
    public void deployApplication() {
        if (!integratorEnvironment.getIntegratorDeploy()) {
            return;
        }

        try {
            checkServerOnline();

            RestTemplate restTemplate = new RestTemplate();
            String resource = integratorEnvironment.getDeployAddress();
            ApplicationRequest application = integratorService.getApplicationRequest();
            ResponseEntity<String> response = restTemplate.postForEntity(resource, application, String.class);

            if (!integratorEnvironment.getIntegratorMock() && response.getStatusCode() != HttpStatus.OK) {
                throw new IllegalStateException("Error while deploying the application on Integrator Server.");
            }

            List<String> proxyAuthorization = response.getHeaders().get(HttpHeaders.PROXY_AUTHORIZATION);
            DecodedJWT decode = JWT.decode(Objects.requireNonNull(proxyAuthorization).get(0));

            integratorService.registryAuthorization(decode.getSubject(), decode.getToken());

            LOGGER.info("Application deployed on Integrator Server.");
        } catch (ResourceAccessException | HttpClientErrorException e) {
            if (!integratorEnvironment.getIntegratorMock()) {
                throw new IllegalStateException("Integrator Server is offline or integrator.host is incorrect.", e);
            } else {
                LOGGER.warning("Integrator Server is offline or integrator.host is incorrect, but is not required for running.");
            }
        }
    }

    private void checkServerOnline() {
        PingServer pingServer = new PingServer();

        if (!pingServer.ping(integratorEnvironment.getIntegratorAddress())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
