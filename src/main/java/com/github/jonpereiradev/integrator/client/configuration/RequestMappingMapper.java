package com.github.jonpereiradev.integrator.client.configuration;

import com.github.jonpereiradev.integrator.client.discovery.Endpoint;
import com.github.jonpereiradev.integrator.client.model.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestMappingMapper {

    private final Class<?> objectClass;

    public RequestMappingMapper(Class<?> objectClass) {
        this.objectClass = objectClass;
    }

    public List<Resource> map(Method endpoint) {
        if (!endpoint.isAnnotationPresent(Endpoint.class)) {
            throw new IllegalArgumentException("Annotation @Endpoint not defined.");
        }

        ResourceType resourceType = ResourceType.valueOf(endpoint.getAnnotations());

        Objects.requireNonNull(resourceType);

        ResourceInfo resourceInfo = resourceType.map(endpoint);
        return registryEndpoint(endpoint.getAnnotation(Endpoint.class), resourceInfo.paths);
    }

    private List<Resource> registryEndpoint(Endpoint endpoint, String[] paths) {
        String context = "";
        List<Resource> resources = new ArrayList<>();

        if (objectClass.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping classAnnotation = objectClass.getAnnotation(RequestMapping.class);
            String[] contextValuesOrPaths = classAnnotation.value();

            if (contextValuesOrPaths.length == 0) {
                contextValuesOrPaths = classAnnotation.path();
            }

            context = resolveAnnotationValue(contextValuesOrPaths);
        }

        if (paths.length == 0) {
            resources.add(new Resource(endpoint.value(), context));
        }

        for (String resource : paths) {
            String resolved = resource.startsWith("/") ? resource : "/" + resource;
            resources.add(new Resource(endpoint.value(), context + resolved));
        }

        return resources;
    }

    private String resolveAnnotationValue(String[] values) {
        if (values.length > 0) {
            String value = values[0];

            if (!values[0].startsWith("/")) {
                value = "/" + values[0];
            }

            return value;
        }

        return "";
    }

    private enum ResourceType {
        REQUEST_MAPPING(RequestMapping.class) {
            @Override
            ResourceInfo map(Method method) {
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                String[] paths = annotation.value().length == 0 ? annotation.path() : annotation.value();
                return new ResourceInfo(paths);
            }
        },
        GET_MAPPING(GetMapping.class) {
            @Override
            ResourceInfo map(Method method) {
                GetMapping annotation = method.getAnnotation(GetMapping.class);
                String[] paths = annotation.value().length == 0 ? annotation.path() : annotation.value();
                return new ResourceInfo(paths);
            }
        },
        POST_MAPPING(PostMapping.class) {
            @Override
            ResourceInfo map(Method method) {
                PostMapping annotation = method.getAnnotation(PostMapping.class);
                String[] paths = annotation.value().length == 0 ? annotation.path() : annotation.value();
                return new ResourceInfo(paths);
            }
        },
        PUT_MAPPING(PutMapping.class) {
            @Override
            ResourceInfo map(Method method) {
                PutMapping annotation = method.getAnnotation(PutMapping.class);
                String[] paths = annotation.value().length == 0 ? annotation.path() : annotation.value();
                return new ResourceInfo(paths);
            }
        },
        DELETE_MAPPING(DeleteMapping.class) {
            @Override
            ResourceInfo map(Method method) {
                DeleteMapping annotation = method.getAnnotation(DeleteMapping.class);
                String[] paths = annotation.value().length == 0 ? annotation.path() : annotation.value();
                return new ResourceInfo(paths);
            }
        };

        final Class<? extends Annotation> annotation;

        ResourceType(Class<? extends Annotation> annotation) {
            this.annotation = annotation;
        }

        abstract ResourceInfo map(Method method);

        public static ResourceType valueOf(Annotation[] annotations) {
            ResourceType type = null;
            
            principal: for (Annotation annotation : annotations) {
                if (!annotation.annotationType().getName().contains("Mapping")) {
                    continue;
                }
                
                for (ResourceType resourceType : values()) {
                    if (annotation.annotationType().equals(resourceType.annotation)) {
                        type = resourceType;
                        break principal;
                    }
                }
            }

            return type;
        }
    }

    private static class ResourceInfo {
        private final String[] paths;

        private ResourceInfo(String[] paths) {
            this.paths = paths;
        }
    }
}
