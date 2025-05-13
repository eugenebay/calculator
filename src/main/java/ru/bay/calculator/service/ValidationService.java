package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.ApplicationConfig;
import ru.bay.calculator.config.ValidationConfig;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class ValidationService {
    private static final Pattern VALID_ROMAN_NUMERAL = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    private final ApplicationConfig applicationConfig;
    private final ValidationConfig validationConfig;

    public ValidationService(ApplicationConfig applicationConfig, ValidationConfig validationConfig) {
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

    public void verifyRomanNumeral(String romanNumeral) throws IllegalArgumentException {
        boolean found = VALID_ROMAN_NUMERAL.matcher(romanNumeral).find();
        if (!found) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }
    }
}
