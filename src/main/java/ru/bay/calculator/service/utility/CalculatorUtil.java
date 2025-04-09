package ru.bay.calculator.service.utility;

import ru.bay.calculator.context.Prototype;

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

    public static boolean isNotInterface(Class<?> draft) {
        return !draft.isInterface();
    }

    public static boolean isSingleton(Class<?> clazz) {
        return !clazz.isAnnotationPresent(Prototype.class);
    }

    public static BufferedReader newBufferedReaderInstance() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    // Suppress SonarQube console output warning.
    @SuppressWarnings("java:S106")
    public static void outputToConsole(String line) {
        System.out.println(line);
    }

    public static Map<Integer, Character> swapKeyValues(Map<Character, Integer> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
