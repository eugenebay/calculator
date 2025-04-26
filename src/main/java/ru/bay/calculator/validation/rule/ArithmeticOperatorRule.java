package ru.bay.calculator.validation.rule;

import java.util.regex.Pattern;

public class ArithmeticOperatorRule implements Rule {
    private static final Pattern PATTERN = Pattern.compile("^[^+\\-*/^]+$");

    @Override
    public String getReason() {
        return "There are no arithmetic operators";
    }

    @Override
    public boolean test(String input) {
        return PATTERN.matcher(input).find();
    }
}
