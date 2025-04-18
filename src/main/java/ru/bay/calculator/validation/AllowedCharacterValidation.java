package ru.bay.calculator.validation;

import ru.bay.calculator.config.ApplicationConfiguration;
import ru.bay.calculator.config.property.ApplicationProperties;

import java.util.HashSet;
import java.util.Set;

public class AllowedCharacterValidation implements Validation {
    private final Set<Character> allowedCharacters;

    public AllowedCharacterValidation(ApplicationConfiguration config) {
        this.allowedCharacters = populateAllowedCharacters(config.getProperties());
    }

    @Override
    public void validate(String inputLine) throws IllegalArgumentException {
        for (char ch : inputLine.toCharArray()) {
            if (allowedCharactersDoesNotContain(ch))
                throw new IllegalArgumentException(String.format("There are incorrect characters in your input '%c'%n", ch));
        }
    }

    private boolean allowedCharactersDoesNotContain(char ch) {
        return !allowedCharacters.contains(ch);
    }

    private Set<Character> populateAllowedCharacters(ApplicationProperties properties) {
        final var set = new HashSet<Character>();
        final var firstAndSingleElementPosition = 0;
        properties.getChars()
                .chars()
                .mapToObj(intRepresentationOfChar -> (char) intRepresentationOfChar)
                .forEach(set::add);
        properties.getOperators()
                .keySet()
                .stream()
                .map(str -> str.charAt(firstAndSingleElementPosition))
                .forEach(set::add);
        return set;
    }
}
