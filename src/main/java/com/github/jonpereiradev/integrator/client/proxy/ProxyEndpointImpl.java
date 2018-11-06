package com.github.jonpereiradev.integrator.client.proxy;

import com.github.jonpereiradev.integrator.client.IntegratorService;
import com.github.jonpereiradev.integrator.client.configuration.IntegratorEnvironment;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Lazy
@Primary
@Component
final class ProxyEndpointImpl extends AbstractProxyEndpoint implements ProxyEndpoint {

    private final IntegratorEnvironment integratorEnvironment;
    private final IntegratorService integratorService;

    @Autowired
    public ProxyEndpointImpl(IntegratorEnvironment integratorEnvironment, IntegratorService integratorService) {
        this.integratorEnvironment = integratorEnvironment;
        this.integratorService = integratorService;
    }

    @Override
    public GetRequest newGetRequest(String resource, ProxyOptions proxyOptions) {
        String proxyResource = IntegratorEnvironment.INTEGRATOR_CONTROLLER_PATH + resource;
        String uri = integratorEnvironment.getIntegratorAddress() + proxyResource;
        GetRequest getRequest = Unirest.get(uri);

        proxyOptions.getHeaders().forEach(param -> getRequest.header(param.getName(), param.getValue()));
        proxyOptions.getPaths().forEach(param -> getRequest.routeParam(param.getName(), param.getValue()));
        proxyOptions.getQueries().forEach(param -> getRequest.queryString(param.getName(), param.getValue()));

        getRequest.header(HttpHeaders.PROXY_AUTHORIZATION, integratorService.getAccessTokenApi(integratorService.getApplication().getId()));
        getRequest.header(HttpHeaders.AUTHORIZATION, integratorService.getAccessTokenApi(asApiKey(resource)));

        return getRequest;
    }

    @Override
    public HttpRequestWithBody newPostRequest(String resource, ProxyOptions proxyOptions) {
        String proxyResource = IntegratorEnvironment.INTEGRATOR_CONTROLLER_PATH + resource;
        String uri = integratorEnvironment.getIntegratorAddress() + proxyResource;
        HttpRequestWithBody postRequest = Unirest.post(uri);

        proxyOptions.getHeaders().forEach(param -> postRequest.header(param.getName(), param.getValue()));
        proxyOptions.getPaths().forEach(param -> postRequest.routeParam(param.getName(), param.getValue()));
        proxyOptions.getQueries().forEach(param -> postRequest.queryString(param.getName(), param.getValue()));

        postRequest.header(HttpHeaders.PROXY_AUTHORIZATION, integratorService.getAccessTokenApi(integratorService.getApplication().getId()));
        postRequest.header(HttpHeaders.AUTHORIZATION, integratorService.getAccessTokenApi(asApiKey(resource)));

        return postRequest;
    }

    @Override
    public HttpRequestWithBody newPutRequest(String resource, ProxyOptions proxyOptions) {
        String proxyResource = IntegratorEnvironment.INTEGRATOR_CONTROLLER_PATH + resource;
        String uri = integratorEnvironment.getIntegratorAddress() + proxyResource;
        HttpRequestWithBody putRequest = Unirest.put(uri);

        proxyOptions.getHeaders().forEach(param -> putRequest.header(param.getName(), param.getValue()));
        proxyOptions.getPaths().forEach(param -> putRequest.routeParam(param.getName(), param.getValue()));
        proxyOptions.getQueries().forEach(param -> putRequest.queryString(param.getName(), param.getValue()));

        putRequest.header(HttpHeaders.PROXY_AUTHORIZATION, integratorService.getAccessTokenApi(integratorService.getApplication().getId()));
        putRequest.header(HttpHeaders.AUTHORIZATION, integratorService.getAccessTokenApi(asApiKey(resource)));

        return putRequest;
    }

    @Override
    public HttpRequestWithBody newPatchRequest(String resource, ProxyOptions proxyOptions) {
        String proxyResource = IntegratorEnvironment.INTEGRATOR_CONTROLLER_PATH + resource;
        String uri = integratorEnvironment.getIntegratorAddress() + proxyResource;
        HttpRequestWithBody patchRequest = Unirest.patch(uri);

        proxyOptions.getHeaders().forEach(param -> patchRequest.header(param.getName(), param.getValue()));
        proxyOptions.getPaths().forEach(param -> patchRequest.routeParam(param.getName(), param.getValue()));
        proxyOptions.getQueries().forEach(param -> patchRequest.queryString(param.getName(), param.getValue()));

        patchRequest.header(HttpHeaders.PROXY_AUTHORIZATION, integratorService.getAccessTokenApi(integratorService.getApplication().getId()));
        patchRequest.header(HttpHeaders.AUTHORIZATION, integratorService.getAccessTokenApi(asApiKey(resource)));

        return patchRequest;
    }

    @Override
    public HttpRequestWithBody newDeleteRequest(String resource, ProxyOptions proxyOptions) {
        String proxyResource = IntegratorEnvironment.INTEGRATOR_CONTROLLER_PATH + resource;
        String uri = integratorEnvironment.getIntegratorAddress() + proxyResource;
        HttpRequestWithBody deleteRequest = Unirest.delete(uri);

        proxyOptions.getHeaders().forEach(param -> deleteRequest.header(param.getName(), param.getValue()));
        proxyOptions.getPaths().forEach(param -> deleteRequest.routeParam(param.getName(), param.getValue()));
        proxyOptions.getQueries().forEach(param -> deleteRequest.queryString(param.getName(), param.getValue()));

        deleteRequest.header(HttpHeaders.PROXY_AUTHORIZATION, integratorService.getAccessTokenApi(integratorService.getApplication().getId()));
        deleteRequest.header(HttpHeaders.AUTHORIZATION, integratorService.getAccessTokenApi(asApiKey(resource)));

        return deleteRequest;
    }

    private String asApiKey(String resource) {
        String apiKey = resource.substring(1);

        if (apiKey.contains("/")) {
            apiKey = apiKey.substring(0, apiKey.indexOf("/"));
        }

        return apiKey;
    }

}
