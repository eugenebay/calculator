package ru.bay.calculator.utility;

import lombok.experimental.UtilityClass;
import ru.bay.calculator.annotation.Disabled;
import ru.bay.calculator.annotation.Prototype;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;

@UtilityClass
public class CalculatorUtil {
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

    public static char[] getSymbolsSortedByValueDesc(Map<Character, Integer> map) {
        var chars = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList();
        var symbols = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) {
            symbols[i] = chars.get(i);
        }
        return symbols;
    }

    public static int[] getDigitValuesForSymbols(char[] symbols, Map<Character, Integer> map) {
        int[] digits = new int[symbols.length];
        for (int i = 0; i < symbols.length; i++) {
            digits[i] = map.get(symbols[i]);
        }
        return digits;
    }
}
