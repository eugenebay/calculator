package ru.bay.calculator.context;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
enum ObjectStorage {
    STORAGE;

    private final List<Class<?>> drafts = new ArrayList<>();
    private final Map<String, Object> store = new HashMap<>();
}
