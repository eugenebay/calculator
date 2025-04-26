package ru.bay.calculator.validation;

import java.util.regex.Pattern;

public class SimpleMathExpressionValidator implements Validation {
    private static final String EXCEPTION_MESSAGE =
            "Invalid math expression format. Expected pattern - [number|roman][operator][number|roman]";
    private static final int LOWER_LIMIT_OF_REPEAT = 2;
    private static final int UPPER_LIMIT_OF_REPEAT = 10;
    private static final String REGEX = String.format(
            "^([IVXLCDM]+|\\d+)([+\\-*/^]([IVXLCDM]+|\\d+)){%d,%d}$",
            LOWER_LIMIT_OF_REPEAT,
            UPPER_LIMIT_OF_REPEAT
    );
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public void validate(String input) throws IllegalArgumentException {
        if (find(input)) throw new IllegalArgumentException(EXCEPTION_MESSAGE);
    }

    private boolean find(String input) {
        return PATTERN.matcher(input).find();
    }
}
