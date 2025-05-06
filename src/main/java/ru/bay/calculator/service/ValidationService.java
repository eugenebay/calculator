package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.ApplicationConfiguration;
import ru.bay.calculator.config.ValidationConfiguration;

import java.util.Objects;

@Component
public class ValidationService {
    private final ApplicationConfiguration applicationConfig;
    private final ValidationConfiguration validationConfig;

    public ValidationService(ApplicationConfiguration applicationConfig, ValidationConfiguration validationConfig) {
        this.applicationConfig = applicationConfig;
        this.validationConfig = validationConfig;
    }

    public String removeSpaces(String input) {
        if (Objects.isNull(input)) throw new IllegalArgumentException("Input string cannot be null");
        return input.replaceAll("\\s", "");
    }

    public boolean isExitCommand(String input) {
        if (Objects.isNull(input)) return false;
        return input.toLowerCase().contains(applicationConfig.getQuitWord());
    }

    public void validationChain(String input) throws IllegalArgumentException {
        validationConfig.getValidations().forEach(validation -> validation.validate(input));
    }
}
