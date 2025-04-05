package ru.bay.calculator.service;

import ru.bay.calculator.config.ApplicationConfig;
import ru.bay.calculator.context.Component;

import java.util.Objects;

@Component
public class ValidationService {
    private static final String EXIT = "exit";

    private final ApplicationConfig config;

    public ValidationService(ApplicationConfig config) {
        this.config = config;
    }

    public boolean isExitCommand(String input) {
        if (Objects.isNull(input)) return false;
        return input.toLowerCase().contains(EXIT);
    }
}
