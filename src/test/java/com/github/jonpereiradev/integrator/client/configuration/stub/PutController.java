package com.github.jonpereiradev.integrator.client.configuration.stub;

import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public final class PutController {

    public static class WithoutContext implements RequestMappingStub {

        @Override
        @PutMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PutMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PutMapping(path = "/put")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PutMapping(path = {"/put1", "/put2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PutMapping(value = "/put")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PutMapping(value = {"/put1", "/put2"})
        public void withValues() {}
    }

    @RequestMapping
    public static class EmptyContext implements RequestMappingStub {

        @Override
        @PutMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PutMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PutMapping(path = "/put")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PutMapping(path = {"/put1", "/put2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PutMapping(value = "/put")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PutMapping(value = {"/put1", "/put2"})
        public void withValues() {}
    }

    @RequestMapping(value = "/context")
    public static class WithValueContext implements RequestMappingStub {

        @Override
        @PutMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PutMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PutMapping(path = "/put")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PutMapping(path = {"/put1", "/put2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PutMapping(value = "/put")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PutMapping(value = {"/put1", "/put2"})
        public void withValues() {}
    }

    @RequestMapping(path = "/context")
    public static class WithPathContext implements RequestMappingStub {

        @Override
        @PutMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PutMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PutMapping(path = "/put")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PutMapping(path = {"/put1", "/put2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PutMapping(value = "/put")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PutMapping(value = {"/put1", "/put2"})
        public void withValues() {}
    }

}
