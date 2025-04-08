package ru.bay.calculator.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Suppress SonarQube enum singleton warning.
@SuppressWarnings("java:S6548")
enum ObjectStorage {
    DRAFTS, STORAGE;

    private final List<Class<?>> drafts = new ArrayList<>();
    private final Map<String, Object> store = new HashMap<>();

    public List<Class<?>> get() {
        return drafts;
    }

    public void addAll(List<Class<?>> classes) {
        drafts.addAll(classes);
    }

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
