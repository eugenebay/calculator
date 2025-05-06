package ru.bay.calculator.validation;

import ru.bay.calculator.config.ApplicationConfig;

public class AllowedCharacterValidation implements Validation {
    private final ApplicationConfig applicationConfig;

    public AllowedCharacterValidation(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public void validate(String input) throws IllegalArgumentException {
        for (char ch : input.toCharArray()) {
            if (allowedCharactersDoesNotContain(ch))
                throw new IllegalArgumentException(String.format("There are incorrect characters in your input '%c'%n", ch));
        }
    }

    private boolean allowedCharactersDoesNotContain(char ch) {
        return !applicationConfig.getAllowedCharacters().contains(ch);
    }
}
