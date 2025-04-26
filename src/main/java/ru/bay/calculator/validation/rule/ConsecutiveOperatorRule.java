package ru.bay.calculator.validation.rule;

import java.util.regex.Pattern;

public class ConsecutiveOperatorRule implements Rule {
    private static final Pattern PATTERN = Pattern.compile("[+\\-*/^]{2,}");

    @Override
    public String getReason() {
        return "Two or more operators in a row";
    }

    @Override
    public boolean test(String input) {
        return PATTERN.matcher(input).find();
    }
}
