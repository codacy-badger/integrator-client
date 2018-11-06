package com.github.jonpereiradev.integrator.client.configuration.stub;

import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public final class GetController {

    public static class WithoutContext implements RequestMappingStub {

        @Override
        @GetMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @GetMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @GetMapping(path = "/get")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @GetMapping(path = {"/get1", "/get2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @GetMapping(value = "/get")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @GetMapping(value = {"/get1", "/get2"})
        public void withValues() {}
    }

    @RequestMapping
    public static class EmptyContext implements RequestMappingStub {

        @Override
        @GetMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @GetMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @GetMapping(path = "/get")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @GetMapping(path = {"/get1", "/get2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @GetMapping(value = "/get")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @GetMapping(value = {"/get1", "/get2"})
        public void withValues() {}
    }

    @RequestMapping(value = "/context")
    public static class WithValueContext implements RequestMappingStub {

        @Override
        @GetMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @GetMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @GetMapping(path = "/get")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @GetMapping(path = {"/get1", "/get2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @GetMapping(value = "/get")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @GetMapping(value = {"/get1", "/get2"})
        public void withValues() {}
    }

    @RequestMapping(path = "/context")
    public static class WithPathContext implements RequestMappingStub {

        @Override
        @GetMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @GetMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @GetMapping(path = "/get")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @GetMapping(path = {"/get1", "/get2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @GetMapping(value = "/get")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @GetMapping(value = {"/get1", "/get2"})
        public void withValues() {}
    }

}
