package ru.bay.calculator.config;

import ru.bay.calculator.config.property.ApplicationProperties;

import java.util.Map;

public class ApplicationConfigurationStub extends ApplicationConfiguration {
    private static final String STOP_WORD = "exit";
    private static final String CHARS = "1234567890IVXLCDM";
    private static final Map<String, String> OPERATORS = Map.of(
            "+", "",
            "-", "",
            "*", "",
            "/", "",
            "^", ""
    );
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
    public ApplicationProperties getProperties() {
        var props = new ApplicationProperties();
        props.setQuitWord(STOP_WORD);
        props.setChars(CHARS);
        props.setOperators(OPERATORS);
        props.setRomans(null); //TODO
        return props;
    }

    private static class TestApplicationConfigurationHolder {
        private static final ApplicationConfigurationStub INSTANCE = new ApplicationConfigurationStub();
    }
}
