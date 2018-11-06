package com.github.jonpereiradev.integrator.client.proxy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.web.client.RestClientException;

import java.io.InputStream;
import java.util.concurrent.Future;

abstract class AbstractProxyEndpoint implements ProxyEndpoint {

    @Override
    public GetRequest newGetRequest(String resource) {
        return newGetRequest(resource, ProxyOptions.DEFAULT_OPTIONS);
    }

    @Override
    public HttpRequestWithBody newPostRequest(String resource) {
        return newPostRequest(resource, ProxyOptions.DEFAULT_OPTIONS);
    }

    @Override
    public HttpRequestWithBody newPutRequest(String resource) {
        return newPutRequest(resource, ProxyOptions.DEFAULT_OPTIONS);
    }

    @Override
    public HttpRequestWithBody newPatchRequest(String resource) {
        return newPatchRequest(resource, ProxyOptions.DEFAULT_OPTIONS);
    }

    @Override
    public HttpRequestWithBody newDeleteRequest(String resource) {
        return newDeleteRequest(resource, ProxyOptions.DEFAULT_OPTIONS);
    }

    @Override
    public HttpResponse<String> asString(BaseRequest request) {
        try {
            return request.asString();
        } catch (UnirestException e) {
            throw new RestClientException(e.getMessage(), e);
        }
    }

    @Override
    public Future<HttpResponse<String>> asStringAsync(BaseRequest request) {
        return request.asStringAsync();
    }

    @Override
    public Future<HttpResponse<String>> asStringAsync(BaseRequest request, Callback<String> callback) {
        return request.asStringAsync(callback);
    }

    @Override
    public HttpResponse<JsonNode> asJson(BaseRequest request) {
        try {
            return request.asJson();
        } catch (UnirestException e) {
            throw new RestClientException(e.getMessage(), e);
        }
    }

    @Override
    public Future<HttpResponse<JsonNode>> asJsonAsync(BaseRequest request) {
        return request.asJsonAsync();
    }

    @Override
    public Future<HttpResponse<JsonNode>> asJsonAsync(BaseRequest request, Callback<JsonNode> callback) {
        return request.asJsonAsync(callback);
    }

    @Override
    public <T> HttpResponse<T> asObject(BaseRequest request, Class<? extends T> responseClass) {
        try {
            return request.asObject(responseClass);
        } catch (UnirestException e) {
            throw new RestClientException(e.getMessage(), e);
        }
    }

    @Override
    public <T> Future<HttpResponse<T>> asObjectAsync(BaseRequest request, Class<? extends T> responseClass) {
        return request.asObjectAsync(responseClass);
    }

    @Override
    public <T> Future<HttpResponse<T>> asObjectAsync(BaseRequest request, Class<? extends T> responseClass, Callback<T> callback) {
        return request.asObjectAsync(responseClass, callback);
    }

    @Override
    public HttpResponse<InputStream> asBinary(BaseRequest request) {
        try {
            return request.asBinary();
        } catch (UnirestException e) {
            throw new RestClientException(e.getMessage(), e);
        }
    }

    @Override
    public Future<HttpResponse<InputStream>> asBinaryAsync(BaseRequest request) {
        return request.asBinaryAsync();
    }

    @Override
    public Future<HttpResponse<InputStream>> asBinaryAsync(BaseRequest request, Callback<InputStream> callback) {
        return request.asBinaryAsync(callback);
    }
}
