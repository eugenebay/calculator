package ru.bay.calculator.config;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApplicationConfigStub implements ApplicationConfig {
    private static final String VERSION = "1.0.0";
    private static final String QUIT_WORD = "exit";
    private static final Set<Character> SIGNS = Set.of('+', '-', '*', '/', '^');
    private static boolean isInstanceCreated = false;

    private ApplicationConfigStub() {
        // Protection from creation via reflection
        if (isInstanceCreated) throw new IllegalStateException("ApplicationConfigStub already initialized.");
        isInstanceCreated = true;
    }

    public static ApplicationConfigStub getInstance() {
        return TestApplicationConfigurationHolder.INSTANCE;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public String getQuitWord() {
        return QUIT_WORD;
    }

    @Override
    public Map<String, String> getOperators() {
        return Map.of(
                "+", "Add",
                "-", "Sub",
                "*", "Multiply",
                "/", "Div",
                "^", "Pow"
        );
    }

    @Override
    public Map<Character, Integer> getRomans() {
        return Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000);
    }

    @Override
    public Set<Character> getAllowedCharacters() {
        var set = new HashSet<Character>();
        set.add('1');
        set.add('2');
        set.add('3');
        set.add('4');
        set.add('5');
        set.add('6');
        set.add('7');
        set.add('8');
        set.add('9');
        set.add('0');
        set.add('I');
        set.add('V');
        set.add('X');
        set.add('L');
        set.add('C');
        set.add('D');
        set.add('M');
        set.addAll(SIGNS);
        return Set.copyOf(set);
    }

    private static class TestApplicationConfigurationHolder {
        private static final ApplicationConfigStub INSTANCE = new ApplicationConfigStub();
    }
}
