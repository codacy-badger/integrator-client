package com.github.jonpereiradev.integrator.client;

import com.github.jonpereiradev.integrator.client.configuration.IntegratorEnvironment;
import com.github.jonpereiradev.integrator.client.configuration.RequestMappingMapper;
import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import com.github.jonpereiradev.integrator.client.model.Application;
import com.github.jonpereiradev.integrator.client.model.ApplicationRequest;
import com.github.jonpereiradev.integrator.client.model.Resource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ApplicationScope
public class IntegratorService {

    private final IntegratorEnvironment integratorEnvironment;
    private final ApplicationContext applicationContext;
    private final Map<String, String> authorizations;

    private ApplicationRequest request;

    @Autowired
    public IntegratorService(IntegratorEnvironment integratorEnvironment, ApplicationContext applicationContext) {
        this.integratorEnvironment = integratorEnvironment;
        this.applicationContext = applicationContext;
        this.authorizations = new ConcurrentHashMap<>();
    }

    public ApplicationRequest getApplicationRequest() {
        if (request == null) {
            request = new ApplicationRequest();
            request.setApplication(new Application());

            request.getApplication().setId(integratorEnvironment.getApplicationId());
            request.getApplication().setName(integratorEnvironment.getApplicationName());
            request.getApplication().setDescription(integratorEnvironment.getApplicationDescription());
            request.getApplication().setVersion(integratorEnvironment.getApplicationVersion());
            request.getApplication().setHost(integratorEnvironment.getAddress());
            request.setSecret(integratorEnvironment.getApplicationSecret());

            Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);

            beans.values().forEach(bean -> {
                Object beanUnwrap = unwrapIfProxy(bean);
                RequestMappingMapper mappingWrapper = new RequestMappingMapper(beanUnwrap.getClass());
                List<Method> methods = Arrays.asList(beanUnwrap.getClass().getDeclaredMethods());

                methods.stream().filter(p -> p.isAnnotationPresent(Endpoint.class)).forEach(endpoint -> {
                    mappingWrapper.map(endpoint).forEach(resource -> request.getResources().add(resource));
                });
            });
        }

        return request;
    }
    
    public Application getApplication() { return getApplicationRequest().getApplication(); }

    public List<Resource> getResources() {
        return getApplicationRequest().getResources();
    }

    public void registryAuthorization(String apiKey, String accessToken) {
        authorizations.put(apiKey, accessToken);
    }

    public String getAccessTokenApi(String apiKey) {
        return authorizations.get(apiKey);
    }

    private Object unwrapIfProxy(Object bean) {
        try {
            if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
                return ((Advised) bean).getTargetSource().getTarget();
            }
        } catch (Exception e) {
            // no fail
        }

        return bean;
    }

}
