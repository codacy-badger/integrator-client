package com.github.jonpereiradev.integrator.client.cache;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Component
public class RequestCacheable implements Cacheable {

    private Map<Class<?>, Map<String, Object>> cacheRequest;

    @PostConstruct
    public void postConstruct() {
        cacheRequest = new ConcurrentHashMap<>();
    }

    @Override
    public <T> Object execute(Class<T> clazz, String identifier, Supplier<T> supplier) {
        if (!cacheRequest.containsKey(clazz)) {
            cacheRequest.put(clazz, new ConcurrentHashMap<>());
        }

        Map<String, Object> cacheClass = cacheRequest.get(clazz);

        if (!cacheClass.containsKey(identifier)) {
            T get = supplier.get();

            if (get != null) {
                cacheClass.put(identifier, get);
            }
        }

        return cacheClass.get(identifier);
    }
}
