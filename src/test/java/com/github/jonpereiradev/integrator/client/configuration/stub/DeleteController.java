package com.github.jonpereiradev.integrator.client.configuration.stub;

import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public final class DeleteController {

    public static class WithoutContext implements RequestMappingStub {

        @Override
        @DeleteMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @DeleteMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @DeleteMapping(path = "/delete")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @DeleteMapping(path = {"/delete1", "/delete2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @DeleteMapping(value = "/delete")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @DeleteMapping(value = {"/delete1", "/delete2"})
        public void withValues() {}
    }

    @RequestMapping
    public static class EmptyContext implements RequestMappingStub {

        @Override
        @DeleteMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @DeleteMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @DeleteMapping(path = "/delete")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @DeleteMapping(path = {"/delete1", "/delete2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @DeleteMapping(value = "/delete")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @DeleteMapping(value = {"/delete1", "/delete2"})
        public void withValues() {}
    }

    @RequestMapping(value = "/context")
    public static class WithValueContext implements RequestMappingStub {

        @Override
        @DeleteMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @DeleteMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @DeleteMapping(path = "/delete")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @DeleteMapping(path = {"/delete1", "/delete2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @DeleteMapping(value = "/delete")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @DeleteMapping(value = {"/delete1", "/delete2"})
        public void withValues() {}
    }

    @RequestMapping(path = "/context")
    public static class WithPathContext implements RequestMappingStub {

        @Override
        @DeleteMapping
        public void notEndpoint() {}

        @Override
        @Endpoint("without")
        @DeleteMapping
        public void without() {}

        @Override
        @Endpoint("with-path")
        @DeleteMapping(path = "/delete")
        public void withPath() {}

        @Override
        @Endpoint("with-paths")
        @DeleteMapping(path = {"/delete1", "/delete2"})
        public void withPaths() {}

        @Override
        @Endpoint("with-value")
        @DeleteMapping(value = "/delete")
        public void withValue() {}

        @Override
        @Endpoint("with-values")
        @DeleteMapping(value = {"/delete1", "/delete2"})
        public void withValues() {}
    }

}
