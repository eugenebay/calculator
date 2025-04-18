package ru.bay.calculator.validation;

public class MaxAllowedInputLengthValidation implements Validation {
    private static final int MAX_INPUT_LENGTH = 23;

    @Override
    public void validate(String inputLine) throws IllegalArgumentException {
        if (inputLine.length() > MAX_INPUT_LENGTH)
            throw new IllegalArgumentException(
                    String.format("The entered string (%d) exceeds the maximum (%d) number of characters.",
                            inputLine.length(),
                            MAX_INPUT_LENGTH)
            );
    }
}
