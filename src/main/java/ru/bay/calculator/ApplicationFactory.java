package ru.bay.calculator;

import lombok.SneakyThrows;
import ru.bay.calculator.exception.ObjectCreationException;
import ru.bay.calculator.property.ApplicationProperties;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.util.*;

public enum ApplicationFactory {
    FACTORY;

    private final Map<Class<?>, Object> cache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<?> draft) {
        if (draft.isInterface()) throw new UnsupportedOperationException("The method does not support interfaces");
        if (cache.containsKey(draft)) return (T) cache.get(draft);
        T instance = createObject(draft);
        // I don't want to look for an implementation for this class, because I don't want to implement interface support.
        // Guilty ^^
        if (instance instanceof ApplicationProperties) {
            cache.put(ApplicationProperties.class, instance);
            return instance;
        }
        cache.put(draft, instance);
        return instance;
    }

    @SuppressWarnings("unchecked")
    private <T> T createObject(Class<?> draft) {
        try {
            return (T) draft.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException ex) {
            // Of course, you need to use a logger, but
            // Nothing is more useful than silence. (c) Menander
        }
        return getTypesForConstructorFromCache(draft);
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private <T> T getTypesForConstructorFromCache(Class<?> draft) {
        Constructor<?>[] constructors = draft.getDeclaredConstructors();
        for (var constructor : constructors) {
            Class<?>[] types = constructor.getParameterTypes();
            if (types.length > 0) {
                var params = new Object[types.length];
                for (int i = 0; i < types.length; i++) {
                    var type = types[i];
                    if (cache.containsKey(type)) {
                        params[i] = cache.get(type);
                    }
                }
                return (T) constructor.newInstance(params);
            }
        }
        throw new ObjectCreationException(String.format("Can't create object - \"%s\"", draft));
    }

    @SneakyThrows
    public List<Class<?>> getDrafts(String route) {
        final List<Class<?>> drafts = new ArrayList<>();
        var dotReplacedRoute = route.replace('.', '/');
        var contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL url = contextClassLoader.getResource(dotReplacedRoute);
        URI uri = null;
        if (Objects.nonNull(url)) {
            uri = url.toURI();
        }
        if (Objects.nonNull(uri)) {
            var dir = new File(uri);
            var files = dir.listFiles();
            if (Objects.nonNull(files)) {
                for (var file : files) {
                    var className = route + "." + file.getName().replace(".class", "");
                    var draft = Class.forName(className);
                    if (!draft.isInterface()) drafts.add(draft);
                }
            }
        }
        return drafts;
    }
}
