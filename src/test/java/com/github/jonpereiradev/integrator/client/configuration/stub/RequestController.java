package com.github.jonpereiradev.integrator.client.configuration.stub;

import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import org.springframework.web.bind.annotation.RequestMapping;

public final class RequestController {

    public static class WithoutContext implements RequestMappingStub {

        @Override
        @RequestMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @RequestMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @RequestMapping(path = "/request")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @RequestMapping(path = {"/request1", "/request2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @RequestMapping(value = "/request")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @RequestMapping(value = {"/request1", "/request2"})
        public void withValues() {}
    }

    @RequestMapping
    public static class EmptyContext implements RequestMappingStub {

        @Override
        @RequestMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @RequestMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @RequestMapping(path = "/request")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @RequestMapping(path = {"/request1", "/request2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @RequestMapping(value = "/request")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @RequestMapping(value = {"/request1", "/request2"})
        public void withValues() {}
    }

    @RequestMapping(value = "context")
    public static class WithValueContext implements RequestMappingStub {

        @Override
        @RequestMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @RequestMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @RequestMapping(path = "/request")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @RequestMapping(path = {"/request1", "/request2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @RequestMapping(value = "/request")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @RequestMapping(value = {"/request1", "/request2"})
        public void withValues() {}
    }

    @RequestMapping(path = "context")
    public static class WithPathContext implements RequestMappingStub {

        @Override
        @RequestMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @RequestMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @RequestMapping(path = "/request")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @RequestMapping(path = {"/request1", "/request2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @RequestMapping(value = "/request")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @RequestMapping(value = {"/request1", "/request2"})
        public void withValues() {}
    }

}
