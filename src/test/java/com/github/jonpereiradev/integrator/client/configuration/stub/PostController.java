package com.github.jonpereiradev.integrator.client.configuration.stub;

import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public final class PostController {

    public static class WithoutContext implements RequestMappingStub {

        @Override
        @PostMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PostMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PostMapping(path = "/post")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PostMapping(path = {"/post1", "/post2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PostMapping(value = "/post")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PostMapping(value = {"/post1", "/post2"})
        public void withValues() {}
    }

    @RequestMapping
    public static class EmptyContext implements RequestMappingStub {

        @Override
        @PostMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PostMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PostMapping(path = "/post")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PostMapping(path = {"/post1", "/post2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PostMapping(value = "/post")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PostMapping(value = {"/post1", "/post2"})
        public void withValues() {}
    }

    @RequestMapping(value = "/context")
    public static class WithValueContext implements RequestMappingStub {

        @Override
        @PostMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PostMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PostMapping(path = "/post")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PostMapping(path = {"/post1", "/post2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PostMapping(value = "/post")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PostMapping(value = {"/post1", "/post2"})
        public void withValues() {}
    }

    @RequestMapping(path = "/context")
    public static class WithPathContext implements RequestMappingStub {

        @Override
        @PostMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @PostMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @PostMapping(path = "/post")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @PostMapping(path = {"/post1", "/post2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @PostMapping(value = "/post")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @PostMapping(value = {"/post1", "/post2"})
        public void withValues() {}
    }

}
