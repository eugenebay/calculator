package ru.bay.calculator.utility;

import ru.bay.calculator.annotation.Disabled;
import ru.bay.calculator.annotation.Prototype;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public final class CalculatorUtil {
    private CalculatorUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Attempt to initialize final class.");
    }

    public static <T> String simplifyName(Class<T> clazz) {
        var simpleName = clazz.getSimpleName();
        return simpleName.replace("Impl", "");
    }

    public static boolean isNotDirectory(Path path) {
        return !Files.isDirectory(path);
    }

    public static boolean isNotInterfaceAndNotDisabled(Class<?> clazz) {
        return isNotInterface(clazz) && isNotDisabled(clazz);
    }

    public static boolean isNotInterface(Class<?> clazz) {
        return !clazz.isInterface();
    }

    public static boolean isNotDisabled(Class<?> clazz) {
        return !clazz.isAnnotationPresent(Disabled.class);
    }

    public static boolean isSingleton(Class<?> clazz) {
        return !clazz.isAnnotationPresent(Prototype.class);
    }

    public static BufferedReader newBufferedReaderInstance() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static Map<Integer, Character> swapKeyValues(Map<Character, Integer> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
