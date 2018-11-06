package com.github.jonpereiradev.integrator.client.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@ApplicationScope
public class IntegratorEnvironment {

    public static final String INTEGRATOR_CONTROLLER_PATH = "/proxy";

    private final Environment environment;

    @Autowired
    public IntegratorEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getServerHost() {
        return InetAddress.getLoopbackAddress().getHostName();
    }

    public String getServerPort() {
        return environment.getProperty(Attrs.SERVER_PORT, "");
    }

    public String getServerContext() {
        return environment.getProperty(Attrs.SERVER_CONTEXT, "");
    }

    public String getIntegratorAddress() {
        return environment.getRequiredProperty(Attrs.INTEGRATOR_HOST);
    }

    public Boolean getIntegratorMock() {
        return environment.getProperty(Attrs.INTEGRATOR_MOCK, Boolean.TYPE, false);
    }

    public Boolean getIntegratorDeploy() {
        return environment.getProperty(Attrs.INTEGRATOR_DEPLOY, Boolean.TYPE, true);
    }

    public Boolean getIntegratorUndeploy() {
        return environment.getProperty(Attrs.INTEGRATOR_UNDEPLOY, Boolean.TYPE, false);
    }

    public String getApplicationId() {
        return environment.getRequiredProperty(Attrs.APPLICATION_ID);
    }

    public String getApplicationName() {
        return environment.getRequiredProperty(Attrs.APPLICATION_NAME);
    }

    public String getApplicationDescription() {
        return environment.getRequiredProperty(Attrs.APPLICATION_DESCRIPTION);
    }

    public String getApplicationSecret() {
        return environment.getRequiredProperty(Attrs.APPLICATION_SECRET);
    }

    public String getApplicationVersion() {
        return environment.getProperty(Attrs.APPLICATION_VERSION, "1.0.0-SNAPSHOT");
    }

    public List<String> getApplicationDependencies() {
        String property = environment.getProperty(Attrs.APPLICATION_DEPENDENCIES, "");
        String[] properties = property.split(",");

        if (properties.length == 0 || (properties.length == 1 && properties[0].isEmpty())) {
            return Collections.emptyList();
        }

        return Arrays.asList(properties);
    }

    public String getAddress() {
        StringBuilder addressBuilder = new StringBuilder("http://");

        addressBuilder.append(getServerHost());

        if (!getServerPort().isEmpty()) {
            addressBuilder.append(":").append(getServerPort());
        }

        return addressBuilder.append(getServerContext()).toString();
    }

    public String getApiAddress(HttpServletRequest request) {
        return getAddress() + INTEGRATOR_CONTROLLER_PATH;
    }

    public String getDeployAddress() {
        return getIntegratorAddress() + "/deploy";
    }

    public String getUndeployAddress() {
        return getIntegratorAddress() + "/undeploy";
    }

}
