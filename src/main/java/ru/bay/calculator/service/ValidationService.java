package ru.bay.calculator.service;

import ru.bay.calculator.config.ApplicationConfiguration;
import ru.bay.calculator.config.ValidationConfiguration;
import ru.bay.calculator.context.Component;

import java.util.Objects;

@Component
public class ValidationService {
    private final ApplicationConfiguration applicationConfig;
    private final ValidationConfiguration validationConfig;

    public ValidationService(ApplicationConfiguration applicationConfig, ValidationConfiguration validationConfig) {
        this.applicationConfig = applicationConfig;
        this.validationConfig = validationConfig;
    }

    public boolean isExitCommand(String input) {
        var quitWord = applicationConfig.getProperties().getQuitWord();
        if (Objects.isNull(input)) return false;
        return input.toLowerCase().contains(quitWord);
    }

    public void validationChain(String input) throws IllegalArgumentException {
        validationConfig.getValidations().forEach(validation -> validation.validate(input));
    }
}
