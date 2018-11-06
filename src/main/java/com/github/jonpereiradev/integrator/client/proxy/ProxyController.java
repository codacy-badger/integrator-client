package com.github.jonpereiradev.integrator.client.proxy;

import com.github.jonpereiradev.integrator.client.configuration.UnirestObjectMapper;
import com.github.jonpereiradev.integrator.client.model.Resource;
import com.github.jonpereiradev.integrator.client.discovery.DiscoveryEndpoint;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/proxy", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProxyController {

    private final ProxyEndpoint proxyEndpoint;
    private final DiscoveryEndpoint discoveryEndpoint;

    @Autowired
    public ProxyController(ProxyEndpoint proxyEndpoint, DiscoveryEndpoint discoveryEndpoint) {
        this.proxyEndpoint = proxyEndpoint;
        this.discoveryEndpoint = discoveryEndpoint;
        Unirest.setObjectMapper(new UnirestObjectMapper());
    }

    @GetMapping("/**")
    public ResponseEntity<String> get(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        int indexOfExpose = requestURI.indexOf("proxy");
        String resource = requestURI.substring(indexOfExpose + "proxy".length() + 1);
        Resource discovery = discoveryEndpoint.discovery(resource);
        ProxyOptions proxyOptions = ProxyOptions.newInstance(request);
        GetRequest getRequest = proxyEndpoint.newGetRequest(discovery.getPath(), proxyOptions);
        HttpResponse<JsonNode> responseEntity = proxyEndpoint.asJson(getRequest);
        HttpStatus status = HttpStatus.valueOf(responseEntity.getStatus());

        if (status == HttpStatus.NOT_FOUND || status == HttpStatus.ACCEPTED) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();

        responseEntity.getHeaders().forEach(headers::put);

        return new ResponseEntity<>(String.valueOf(responseEntity.getBody()), headers, status);
    }

    @PostMapping("/**")
    public ResponseEntity<String> post(HttpServletRequest request, @RequestBody(required = false) String body) {
        String requestURI = request.getRequestURI();
        int indexOfExpose = requestURI.indexOf("proxy");
        String resource = requestURI.substring(indexOfExpose + "proxy".length() + 1);
        Resource discovery = discoveryEndpoint.discovery(resource);

        indexOfExpose = discovery.getPath().indexOf("proxy");

        if (indexOfExpose != -1) {
            resource = discovery.getPath().substring(indexOfExpose + "proxy".length());
        }

        ProxyOptions proxyOptions = ProxyOptions.newInstance(request);
        RequestBodyEntity postRequest = proxyEndpoint.newPostRequest(resource, proxyOptions).body(body);
        HttpResponse<JsonNode> responseEntity = proxyEndpoint.asJson(postRequest);
        HttpStatus status = HttpStatus.valueOf(responseEntity.getStatus());

        if (status == HttpStatus.NOT_FOUND || status == HttpStatus.ACCEPTED) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();

        responseEntity.getHeaders().forEach(headers::put);

        return new ResponseEntity<>(String.valueOf(responseEntity.getBody()), headers, status);
    }

    @PutMapping("/**")
    public ResponseEntity<String> put(HttpServletRequest request, @RequestBody(required = false) String body) {
        String requestURI = request.getRequestURI();
        int indexOfExpose = requestURI.indexOf("proxy");
        String resource = requestURI.substring(indexOfExpose + "proxy".length() + 1);
        Resource discovery = discoveryEndpoint.discovery(resource);
        ProxyOptions proxyOptions = ProxyOptions.newInstance(request);
        RequestBodyEntity putRequest = proxyEndpoint.newPutRequest(discovery.getPath(), proxyOptions).body(body);
        HttpResponse<JsonNode> responseEntity = proxyEndpoint.asJson(putRequest);
        HttpStatus status = HttpStatus.valueOf(responseEntity.getStatus());

        if (status == HttpStatus.NOT_FOUND || status == HttpStatus.ACCEPTED) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();

        responseEntity.getHeaders().forEach(headers::put);

        return new ResponseEntity<>(String.valueOf(responseEntity.getBody()), headers, status);
    }

    @DeleteMapping("/**")
    public ResponseEntity<String> delete(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        int indexOfExpose = requestURI.indexOf("proxy");
        String resource = requestURI.substring(indexOfExpose + "proxy".length() + 1);
        Resource discovery = discoveryEndpoint.discovery(resource);
        ProxyOptions proxyOptions = ProxyOptions.newInstance(request);
        HttpRequestWithBody deleteRequest = proxyEndpoint.newDeleteRequest(discovery.getPath(), proxyOptions);
        HttpResponse<JsonNode> responseEntity = proxyEndpoint.asJson(deleteRequest);
        HttpStatus status = HttpStatus.valueOf(responseEntity.getStatus());

        if (status == HttpStatus.NOT_FOUND || status == HttpStatus.ACCEPTED) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();

        responseEntity.getHeaders().forEach(headers::put);

        return new ResponseEntity<>(String.valueOf(responseEntity.getBody()), headers, status);
    }

}
