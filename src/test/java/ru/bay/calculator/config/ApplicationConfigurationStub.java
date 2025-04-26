package ru.bay.calculator.config;

import java.util.HashSet;
import java.util.Set;

public class ApplicationConfigurationStub extends ApplicationConfiguration {
    private static final String QUIT_WORD = "exit";
    private static final Set<Character> SIGNS = Set.of('+', '-', '*', '/', '^');
    private static boolean isInstanceCreated = false;

    private ApplicationConfigurationStub() {
        // Protection from creation via reflection
        if (isInstanceCreated) throw new IllegalStateException("ApplicationConfiguration already initialized.");
        isInstanceCreated = true;
    }

    public static ApplicationConfigurationStub getInstance() {
        return TestApplicationConfigurationHolder.INSTANCE;
    }

    @Override
    public String getQuitWord() {
        return QUIT_WORD;
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
        private static final ApplicationConfigurationStub INSTANCE = new ApplicationConfigurationStub();
    }
}
