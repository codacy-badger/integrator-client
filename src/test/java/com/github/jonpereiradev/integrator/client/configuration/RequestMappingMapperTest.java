package com.github.jonpereiradev.integrator.client.configuration;

import com.github.jonpereiradev.integrator.client.configuration.stub.DeleteController;
import com.github.jonpereiradev.integrator.client.configuration.stub.GetController;
import com.github.jonpereiradev.integrator.client.configuration.stub.PostController;
import com.github.jonpereiradev.integrator.client.configuration.stub.PutController;
import com.github.jonpereiradev.integrator.client.configuration.stub.RequestController;
import com.github.jonpereiradev.integrator.client.model.Resource;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class RequestMappingMapperTest {

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnRequestControllerWithoutContext() throws NoSuchMethodException {
        try {
            Class<RequestController.WithoutContext> clazz = RequestController.WithoutContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnRequestControllerWithoutContext() throws NoSuchMethodException {
        Class<RequestController.WithoutContext> clazz = RequestController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnRequestControllerWithoutContext() throws NoSuchMethodException {
        Class<RequestController.WithoutContext> clazz = RequestController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/request");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnRequestControllerWithoutContext() throws NoSuchMethodException {
        Class<RequestController.WithoutContext> clazz = RequestController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/request1");
        assertResource(resources.get(1), "with-paths", "/request2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnRequestControllerWithoutContext() throws NoSuchMethodException {
        Class<RequestController.WithoutContext> clazz = RequestController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/request");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnRequestControllerWithoutContext() throws NoSuchMethodException {
        Class<RequestController.WithoutContext> clazz = RequestController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/request1");
        assertResource(resources.get(1), "with-values", "/request2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnRequestControllerEmptyContext() throws NoSuchMethodException {
        try {
            Class<RequestController.EmptyContext> clazz = RequestController.EmptyContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnRequestControllerEmptyContext() throws NoSuchMethodException {
        Class<RequestController.EmptyContext> clazz = RequestController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnRequestControllerEmptyContext() throws NoSuchMethodException {
        Class<RequestController.EmptyContext> clazz = RequestController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/request");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnRequestControllerEmptyContext() throws NoSuchMethodException {
        Class<RequestController.EmptyContext> clazz = RequestController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/request1");
        assertResource(resources.get(1), "with-paths", "/request2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnRequestControllerEmptyContext() throws NoSuchMethodException {
        Class<RequestController.EmptyContext> clazz = RequestController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/request");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnRequestControllerEmptyContext() throws NoSuchMethodException {
        Class<RequestController.EmptyContext> clazz = RequestController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/request1");
        assertResource(resources.get(1), "with-values", "/request2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnRequestControllerWithValueContext() throws NoSuchMethodException {
        try {
            Class<RequestController.WithValueContext> clazz = RequestController.WithValueContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnRequestControllerWithValueContext() throws NoSuchMethodException {
        Class<RequestController.WithValueContext> clazz = RequestController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnRequestControllerWithValueContext() throws NoSuchMethodException {
        Class<RequestController.WithValueContext> clazz = RequestController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/request");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnRequestControllerWithValueContext() throws NoSuchMethodException {
        Class<RequestController.WithValueContext> clazz = RequestController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/request1");
        assertResource(resources.get(1), "with-paths", "/context/request2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnRequestControllerWithValueContext() throws NoSuchMethodException {
        Class<RequestController.WithValueContext> clazz = RequestController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/request");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnRequestControllerWithValueContext() throws NoSuchMethodException {
        Class<RequestController.WithValueContext> clazz = RequestController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/request1");
        assertResource(resources.get(1), "with-values", "/context/request2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnRequestControllerWithPathContext() throws NoSuchMethodException {
        try {
            Class<RequestController.WithPathContext> clazz = RequestController.WithPathContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnRequestControllerWithPathContext() throws NoSuchMethodException {
        Class<RequestController.WithPathContext> clazz = RequestController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnRequestControllerWithPathContext() throws NoSuchMethodException {
        Class<RequestController.WithPathContext> clazz = RequestController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/request");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnRequestControllerWithPathContext() throws NoSuchMethodException {
        Class<RequestController.WithPathContext> clazz = RequestController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/request1");
        assertResource(resources.get(1), "with-paths", "/context/request2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnRequestControllerWithPathContext() throws NoSuchMethodException {
        Class<RequestController.WithPathContext> clazz = RequestController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/request");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnRequestControllerWithPathContext() throws NoSuchMethodException {
        Class<RequestController.WithPathContext> clazz = RequestController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/request1");
        assertResource(resources.get(1), "with-values", "/context/request2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnGetControllerWithoutContext() throws NoSuchMethodException {
        try {
            Class<GetController.WithoutContext> clazz = GetController.WithoutContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnGetControllerWithoutContext() throws NoSuchMethodException {
        Class<GetController.WithoutContext> clazz = GetController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnGetControllerWithoutContext() throws NoSuchMethodException {
        Class<GetController.WithoutContext> clazz = GetController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/get");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnGetControllerWithoutContext() throws NoSuchMethodException {
        Class<GetController.WithoutContext> clazz = GetController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/get1");
        assertResource(resources.get(1), "with-paths", "/get2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnGetControllerWithoutContext() throws NoSuchMethodException {
        Class<GetController.WithoutContext> clazz = GetController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/get");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnGetControllerWithoutContext() throws NoSuchMethodException {
        Class<GetController.WithoutContext> clazz = GetController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/get1");
        assertResource(resources.get(1), "with-values", "/get2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnGetControllerEmptyContext() throws NoSuchMethodException {
        try {
            Class<GetController.EmptyContext> clazz = GetController.EmptyContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnGetControllerEmptyContext() throws NoSuchMethodException {
        Class<GetController.EmptyContext> clazz = GetController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnGetControllerEmptyContext() throws NoSuchMethodException {
        Class<GetController.EmptyContext> clazz = GetController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/get");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnGetControllerEmptyContext() throws NoSuchMethodException {
        Class<GetController.EmptyContext> clazz = GetController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/get1");
        assertResource(resources.get(1), "with-paths", "/get2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnGetControllerEmptyContext() throws NoSuchMethodException {
        Class<GetController.EmptyContext> clazz = GetController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/get");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnGetControllerEmptyContext() throws NoSuchMethodException {
        Class<GetController.EmptyContext> clazz = GetController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/get1");
        assertResource(resources.get(1), "with-values", "/get2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnGetControllerWithValueContext() throws NoSuchMethodException {
        try {
            Class<GetController.WithValueContext> clazz = GetController.WithValueContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnGetControllerWithValueContext() throws NoSuchMethodException {
        Class<GetController.WithValueContext> clazz = GetController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnGetControllerWithValueContext() throws NoSuchMethodException {
        Class<GetController.WithValueContext> clazz = GetController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/get");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnGetControllerWithValueContext() throws NoSuchMethodException {
        Class<GetController.WithValueContext> clazz = GetController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/get1");
        assertResource(resources.get(1), "with-paths", "/context/get2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnGetControllerWithValueContext() throws NoSuchMethodException {
        Class<GetController.WithValueContext> clazz = GetController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/get");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnGetControllerWithValueContext() throws NoSuchMethodException {
        Class<GetController.WithValueContext> clazz = GetController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/get1");
        assertResource(resources.get(1), "with-values", "/context/get2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnGetControllerWithPathContext() throws NoSuchMethodException {
        try {
            Class<GetController.WithPathContext> clazz = GetController.WithPathContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnGetControllerWithPathContext() throws NoSuchMethodException {
        Class<GetController.WithPathContext> clazz = GetController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnGetControllerWithPathContext() throws NoSuchMethodException {
        Class<GetController.WithPathContext> clazz = GetController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/get");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnGetControllerWithPathContext() throws NoSuchMethodException {
        Class<GetController.WithPathContext> clazz = GetController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/get1");
        assertResource(resources.get(1), "with-paths", "/context/get2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnGetControllerWithPathContext() throws NoSuchMethodException {
        Class<GetController.WithPathContext> clazz = GetController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/get");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnGetControllerWithPathContext() throws NoSuchMethodException {
        Class<GetController.WithPathContext> clazz = GetController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/get1");
        assertResource(resources.get(1), "with-values", "/context/get2");
    }


    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPostControllerWithoutContext() throws NoSuchMethodException {
        try {
            Class<PostController.WithoutContext> clazz = PostController.WithoutContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPostControllerWithoutContext() throws NoSuchMethodException {
        Class<PostController.WithoutContext> clazz = PostController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPostControllerWithoutContext() throws NoSuchMethodException {
        Class<PostController.WithoutContext> clazz = PostController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/post");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPostControllerWithoutContext() throws NoSuchMethodException {
        Class<PostController.WithoutContext> clazz = PostController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/post1");
        assertResource(resources.get(1), "with-paths", "/post2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPostControllerWithoutContext() throws NoSuchMethodException {
        Class<PostController.WithoutContext> clazz = PostController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/post");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPostControllerWithoutContext() throws NoSuchMethodException {
        Class<PostController.WithoutContext> clazz = PostController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/post1");
        assertResource(resources.get(1), "with-values", "/post2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPostControllerEmptyContext() throws NoSuchMethodException {
        try {
            Class<PostController.EmptyContext> clazz = PostController.EmptyContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPostControllerEmptyContext() throws NoSuchMethodException {
        Class<PostController.EmptyContext> clazz = PostController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPostControllerEmptyContext() throws NoSuchMethodException {
        Class<PostController.EmptyContext> clazz = PostController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/post");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPostControllerEmptyContext() throws NoSuchMethodException {
        Class<PostController.EmptyContext> clazz = PostController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/post1");
        assertResource(resources.get(1), "with-paths", "/post2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPostControllerEmptyContext() throws NoSuchMethodException {
        Class<PostController.EmptyContext> clazz = PostController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/post");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPostControllerEmptyContext() throws NoSuchMethodException {
        Class<PostController.EmptyContext> clazz = PostController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/post1");
        assertResource(resources.get(1), "with-values", "/post2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPostControllerWithValueContext() throws NoSuchMethodException {
        try {
            Class<PostController.WithValueContext> clazz = PostController.WithValueContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPostControllerWithValueContext() throws NoSuchMethodException {
        Class<PostController.WithValueContext> clazz = PostController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPostControllerWithValueContext() throws NoSuchMethodException {
        Class<PostController.WithValueContext> clazz = PostController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/post");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPostControllerWithValueContext() throws NoSuchMethodException {
        Class<PostController.WithValueContext> clazz = PostController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/post1");
        assertResource(resources.get(1), "with-paths", "/context/post2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPostControllerWithValueContext() throws NoSuchMethodException {
        Class<PostController.WithValueContext> clazz = PostController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/post");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPostControllerWithValueContext() throws NoSuchMethodException {
        Class<PostController.WithValueContext> clazz = PostController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/post1");
        assertResource(resources.get(1), "with-values", "/context/post2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPostControllerWithPathContext() throws NoSuchMethodException {
        try {
            Class<PostController.WithPathContext> clazz = PostController.WithPathContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPostControllerWithPathContext() throws NoSuchMethodException {
        Class<PostController.WithPathContext> clazz = PostController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPostControllerWithPathContext() throws NoSuchMethodException {
        Class<PostController.WithPathContext> clazz = PostController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/post");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPostControllerWithPathContext() throws NoSuchMethodException {
        Class<PostController.WithPathContext> clazz = PostController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/post1");
        assertResource(resources.get(1), "with-paths", "/context/post2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPostControllerWithPathContext() throws NoSuchMethodException {
        Class<PostController.WithPathContext> clazz = PostController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/post");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPostControllerWithPathContext() throws NoSuchMethodException {
        Class<PostController.WithPathContext> clazz = PostController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/post1");
        assertResource(resources.get(1), "with-values", "/context/post2");
    }


    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPutControllerWithoutContext() throws NoSuchMethodException {
        try {
            Class<PutController.WithoutContext> clazz = PutController.WithoutContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPutControllerWithoutContext() throws NoSuchMethodException {
        Class<PutController.WithoutContext> clazz = PutController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPutControllerWithoutContext() throws NoSuchMethodException {
        Class<PutController.WithoutContext> clazz = PutController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/put");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPutControllerWithoutContext() throws NoSuchMethodException {
        Class<PutController.WithoutContext> clazz = PutController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/put1");
        assertResource(resources.get(1), "with-paths", "/put2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPutControllerWithoutContext() throws NoSuchMethodException {
        Class<PutController.WithoutContext> clazz = PutController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/put");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPutControllerWithoutContext() throws NoSuchMethodException {
        Class<PutController.WithoutContext> clazz = PutController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/put1");
        assertResource(resources.get(1), "with-values", "/put2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPutControllerEmptyContext() throws NoSuchMethodException {
        try {
            Class<PutController.EmptyContext> clazz = PutController.EmptyContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPutControllerEmptyContext() throws NoSuchMethodException {
        Class<PutController.EmptyContext> clazz = PutController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPutControllerEmptyContext() throws NoSuchMethodException {
        Class<PutController.EmptyContext> clazz = PutController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/put");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPutControllerEmptyContext() throws NoSuchMethodException {
        Class<PutController.EmptyContext> clazz = PutController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/put1");
        assertResource(resources.get(1), "with-paths", "/put2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPutControllerEmptyContext() throws NoSuchMethodException {
        Class<PutController.EmptyContext> clazz = PutController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/put");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPutControllerEmptyContext() throws NoSuchMethodException {
        Class<PutController.EmptyContext> clazz = PutController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/put1");
        assertResource(resources.get(1), "with-values", "/put2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPutControllerWithValueContext() throws NoSuchMethodException {
        try {
            Class<PutController.WithValueContext> clazz = PutController.WithValueContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPutControllerWithValueContext() throws NoSuchMethodException {
        Class<PutController.WithValueContext> clazz = PutController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPutControllerWithValueContext() throws NoSuchMethodException {
        Class<PutController.WithValueContext> clazz = PutController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/put");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPutControllerWithValueContext() throws NoSuchMethodException {
        Class<PutController.WithValueContext> clazz = PutController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/put1");
        assertResource(resources.get(1), "with-paths", "/context/put2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPutControllerWithValueContext() throws NoSuchMethodException {
        Class<PutController.WithValueContext> clazz = PutController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/put");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPutControllerWithValueContext() throws NoSuchMethodException {
        Class<PutController.WithValueContext> clazz = PutController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/put1");
        assertResource(resources.get(1), "with-values", "/context/put2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnPutControllerWithPathContext() throws NoSuchMethodException {
        try {
            Class<PutController.WithPathContext> clazz = PutController.WithPathContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnPutControllerWithPathContext() throws NoSuchMethodException {
        Class<PutController.WithPathContext> clazz = PutController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnPutControllerWithPathContext() throws NoSuchMethodException {
        Class<PutController.WithPathContext> clazz = PutController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/put");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnPutControllerWithPathContext() throws NoSuchMethodException {
        Class<PutController.WithPathContext> clazz = PutController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/put1");
        assertResource(resources.get(1), "with-paths", "/context/put2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnPutControllerWithPathContext() throws NoSuchMethodException {
        Class<PutController.WithPathContext> clazz = PutController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/put");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnPutControllerWithPathContext() throws NoSuchMethodException {
        Class<PutController.WithPathContext> clazz = PutController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/put1");
        assertResource(resources.get(1), "with-values", "/context/put2");
    }


    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnDeleteControllerWithoutContext() throws NoSuchMethodException {
        try {
            Class<DeleteController.WithoutContext> clazz = DeleteController.WithoutContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnDeleteControllerWithoutContext() throws NoSuchMethodException {
        Class<DeleteController.WithoutContext> clazz = DeleteController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnDeleteControllerWithoutContext() throws NoSuchMethodException {
        Class<DeleteController.WithoutContext> clazz = DeleteController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/delete");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnDeleteControllerWithoutContext() throws NoSuchMethodException {
        Class<DeleteController.WithoutContext> clazz = DeleteController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/delete1");
        assertResource(resources.get(1), "with-paths", "/delete2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnDeleteControllerWithoutContext() throws NoSuchMethodException {
        Class<DeleteController.WithoutContext> clazz = DeleteController.WithoutContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/delete");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnDeleteControllerWithoutContext() throws NoSuchMethodException {
        Class<DeleteController.WithoutContext> clazz = DeleteController.WithoutContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/delete1");
        assertResource(resources.get(1), "with-values", "/delete2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnDeleteControllerEmptyContext() throws NoSuchMethodException {
        try {
            Class<DeleteController.EmptyContext> clazz = DeleteController.EmptyContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnDeleteControllerEmptyContext() throws NoSuchMethodException {
        Class<DeleteController.EmptyContext> clazz = DeleteController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnDeleteControllerEmptyContext() throws NoSuchMethodException {
        Class<DeleteController.EmptyContext> clazz = DeleteController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/delete");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnDeleteControllerEmptyContext() throws NoSuchMethodException {
        Class<DeleteController.EmptyContext> clazz = DeleteController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/delete1");
        assertResource(resources.get(1), "with-paths", "/delete2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnDeleteControllerEmptyContext() throws NoSuchMethodException {
        Class<DeleteController.EmptyContext> clazz = DeleteController.EmptyContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/delete");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnDeleteControllerEmptyContext() throws NoSuchMethodException {
        Class<DeleteController.EmptyContext> clazz = DeleteController.EmptyContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/delete1");
        assertResource(resources.get(1), "with-values", "/delete2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnDeleteControllerWithValueContext() throws NoSuchMethodException {
        try {
            Class<DeleteController.WithValueContext> clazz = DeleteController.WithValueContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnDeleteControllerWithValueContext() throws NoSuchMethodException {
        Class<DeleteController.WithValueContext> clazz = DeleteController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnDeleteControllerWithValueContext() throws NoSuchMethodException {
        Class<DeleteController.WithValueContext> clazz = DeleteController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/delete");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnDeleteControllerWithValueContext() throws NoSuchMethodException {
        Class<DeleteController.WithValueContext> clazz = DeleteController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/delete1");
        assertResource(resources.get(1), "with-paths", "/context/delete2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnDeleteControllerWithValueContext() throws NoSuchMethodException {
        Class<DeleteController.WithValueContext> clazz = DeleteController.WithValueContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/delete");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnDeleteControllerWithValueContext() throws NoSuchMethodException {
        Class<DeleteController.WithValueContext> clazz = DeleteController.WithValueContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/delete1");
        assertResource(resources.get(1), "with-values", "/context/delete2");
    }

    @Test
    public void mustThrowExceptionWhenMethodWithoutEndpointOnDeleteControllerWithPathContext() throws NoSuchMethodException {
        try {
            Class<DeleteController.WithPathContext> clazz = DeleteController.WithPathContext.class;
            RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
            requestMappingMapper.map(clazz.getDeclaredMethod("notEndpoint"));
            fail("Must throw exception when @Endpoint not defined.");
        } catch (IllegalArgumentException iae) {
            assertEquals("Annotation @Endpoint not defined.", iae.getMessage());
        }
    }

    @Test
    public void mustMapWithoutWhenWithoutMethodOnDeleteControllerWithPathContext() throws NoSuchMethodException {
        Class<DeleteController.WithPathContext> clazz = DeleteController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("without"));

        assertNotNull(resource);
        assertResource(resource, "without", "/context");
    }

    @Test
    public void mustMapWithoutWhenWithPathMethodOnDeleteControllerWithPathContext() throws NoSuchMethodException {
        Class<DeleteController.WithPathContext> clazz = DeleteController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withPath"));

        assertNotNull(resource);
        assertResource(resource, "with-path", "/context/delete");
    }

    @Test
    public void mustMapWithoutWhenWithPathsMethodOnDeleteControllerWithPathContext() throws NoSuchMethodException {
        Class<DeleteController.WithPathContext> clazz = DeleteController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withPaths"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-paths", "/context/delete1");
        assertResource(resources.get(1), "with-paths", "/context/delete2");
    }

    @Test
    public void mustMapWithoutWhenWithValueMethodOnDeleteControllerWithPathContext() throws NoSuchMethodException {
        Class<DeleteController.WithPathContext> clazz = DeleteController.WithPathContext.class;
        Resource resource = map(clazz, clazz.getDeclaredMethod("withValue"));

        assertNotNull(resource);
        assertResource(resource, "with-value", "/context/delete");
    }

    @Test
    public void mustMapWithoutWhenWithValuesMethodOnDeleteControllerWithPathContext() throws NoSuchMethodException {
        Class<DeleteController.WithPathContext> clazz = DeleteController.WithPathContext.class;
        List<Resource> resources = mapList(clazz, clazz.getDeclaredMethod("withValues"));

        assertNotNull(resources);
        assertEquals(2, resources.size());
        assertResource(resources.get(0), "with-values", "/context/delete1");
        assertResource(resources.get(1), "with-values", "/context/delete2");
    }

    private static Resource map(Class<?> clazz, Method method) {
        RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
        List<Resource> resources = requestMappingMapper.map(method);

        assertFalse(resources.isEmpty());
        assertEquals(1, resources.size());

        return resources.get(0);
    }

    private static List<Resource> mapList(Class<?> clazz, Method method) {
        RequestMappingMapper requestMappingMapper = new RequestMappingMapper(clazz);
        List<Resource> resources = requestMappingMapper.map(method);

        assertFalse(resources.isEmpty());

        return resources;
    }

    private static void assertResource(Resource resource, String identifier, String path) {
        assertEquals(identifier, resource.getIdentifier());
        assertEquals(path, resource.getPath());
    }

}