package ru.bay.calculator.service.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public final class CalculatorUtils {
    private CalculatorUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Attempt to initialize final class.");
    }

    public static BufferedReader newBufferedReaderInstance() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static Map<Integer, Character> swapKeyValues(Map<Character, Integer> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
