package ru.bay.calculator.validation;

import ru.bay.calculator.config.ApplicationConfiguration;

public class AllowedCharacterValidation implements Validation {
    private final ApplicationConfiguration config;

    public AllowedCharacterValidation(ApplicationConfiguration config) {
        this.config = config;
    }

    @Override
    public void validate(String input) throws IllegalArgumentException {
        for (char ch : input.toCharArray()) {
            if (allowedCharactersDoesNotContain(ch))
                throw new IllegalArgumentException(String.format("There are incorrect characters in your input '%c'%n", ch));
        }
    }

    private boolean allowedCharactersDoesNotContain(char ch) {
        return !config.getAllowedCharacters().contains(ch);
    }
}
