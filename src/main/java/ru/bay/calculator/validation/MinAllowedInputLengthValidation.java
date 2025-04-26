package ru.bay.calculator.validation;

public class MinAllowedInputLengthValidation implements Validation {
    private static final int MIN_INPUT_LENGTH = 3;

    @Override
    public void validate(String input) throws IllegalArgumentException {
        if (input.length() < MIN_INPUT_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("The minimum number of characters to enter is (%d), you entered (%d)",
                            MIN_INPUT_LENGTH,
                            input.length())
            );
        }
    }
}
