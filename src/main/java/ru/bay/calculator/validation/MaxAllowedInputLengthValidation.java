package ru.bay.calculator.validation;

public class MaxAllowedInputLengthValidation implements Validation {
    private static final int MAX_INPUT_LENGTH = 23;

    @Override
    public void validate(String input) throws IllegalArgumentException {
        if (input.length() > MAX_INPUT_LENGTH)
            throw new IllegalArgumentException(
                    String.format("The entered string (%d) exceeds the maximum (%d) number of characters.",
                            input.length(),
                            MAX_INPUT_LENGTH)
            );
    }
}
