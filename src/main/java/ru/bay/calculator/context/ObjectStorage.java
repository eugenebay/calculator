package ru.bay.calculator.context;

import java.util.HashMap;
import java.util.Map;

// Suppress SonarQube enum singleton warning.
@SuppressWarnings("java:S6548")
enum ObjectStorage {
    STORAGE;

    private final Map<String, Object> store = new HashMap<>();

    public Object get(String className) {
        return store.get(className);
    }

    public <T> void put(String className, T instance) {
        store.put(className, instance);
    }

    public boolean contains(String className) {
        return store.containsKey(className);
    }
}
