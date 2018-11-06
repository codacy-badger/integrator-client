package com.github.jonpereiradev.integrator.client.cache;

import java.util.function.Supplier;

public interface Cacheable {

    <T> Object execute(Class<T> clazz, String identifier, Supplier<T> supplier);

}
