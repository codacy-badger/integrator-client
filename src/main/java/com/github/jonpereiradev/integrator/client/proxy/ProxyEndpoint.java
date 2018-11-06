package com.github.jonpereiradev.integrator.client.proxy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.request.BaseRequest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.InputStream;
import java.util.concurrent.Future;

public interface ProxyEndpoint {

    GetRequest newGetRequest(String resource);

    GetRequest newGetRequest(String resource, ProxyOptions proxyOptions);

    HttpRequestWithBody newPostRequest(String resource);

    HttpRequestWithBody newPostRequest(String resource, ProxyOptions proxyOptions);

    HttpRequestWithBody newPutRequest(String resource);

    HttpRequestWithBody newPutRequest(String resource, ProxyOptions proxyOptions);

    HttpRequestWithBody newPatchRequest(String resource);

    HttpRequestWithBody newPatchRequest(String resource, ProxyOptions proxyOptions);

    HttpRequestWithBody newDeleteRequest(String resource);

    HttpRequestWithBody newDeleteRequest(String resource, ProxyOptions proxyOptions);

    HttpResponse<String> asString(BaseRequest request);

    Future<HttpResponse<String>> asStringAsync(BaseRequest request);

    Future<HttpResponse<String>> asStringAsync(BaseRequest request, Callback<String> callback);

    HttpResponse<JsonNode> asJson(BaseRequest request);

    Future<HttpResponse<JsonNode>> asJsonAsync(BaseRequest request);

    Future<HttpResponse<JsonNode>> asJsonAsync(BaseRequest request, Callback<JsonNode> callback);

    <T> HttpResponse<T> asObject(BaseRequest request, Class<? extends T> responseClass);

    <T> Future<HttpResponse<T>> asObjectAsync(BaseRequest request, Class<? extends T> responseClass);

    <T> Future<HttpResponse<T>> asObjectAsync(BaseRequest request, Class<? extends T> responseClass, Callback<T> callback);

    HttpResponse<InputStream> asBinary(BaseRequest request);

    Future<HttpResponse<InputStream>> asBinaryAsync(BaseRequest request);

    Future<HttpResponse<InputStream>> asBinaryAsync(BaseRequest request, Callback<InputStream> callback);

}
