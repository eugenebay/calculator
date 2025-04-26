package ru.bay.calculator.validation.rule;

import java.util.regex.Pattern;

public class TrailingOperatorRule implements Rule {
    private static final Pattern PATTERN = Pattern.compile(".*[+\\-*/^]$");

    @Override
    public String getReason() {
        return "Operator not allowed at end of expression";
    }

    @Override
    public boolean test(String input) {
        return PATTERN.matcher(input).find();
    }
}
