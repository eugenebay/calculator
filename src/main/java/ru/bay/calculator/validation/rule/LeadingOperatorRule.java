package ru.bay.calculator.validation.rule;

import java.util.regex.Pattern;

public class LeadingOperatorRule implements Rule {
    private static final Pattern PATTERN = Pattern.compile("^(?!-?\\d)[+*/^-].*");

    @Override
    public String getReason() {
        return "Invalid character at beginning of expression";
    }

    @Override
    public boolean test(String input) {
        return PATTERN.matcher(input).find();
    }
}
