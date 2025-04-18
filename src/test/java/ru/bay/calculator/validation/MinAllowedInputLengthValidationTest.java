package ru.bay.calculator.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinAllowedInputLengthValidationTest {
    private MinAllowedInputLengthValidation validation;

    @BeforeEach
    void setValidation() {
        validation = new MinAllowedInputLengthValidation();
    }

    @ParameterizedTest
    @CsvSource({" -", "1", "12"})
    void shouldReturnIllegalArgumentExceptionWhenInputStringIsMinAllowedInputLength(String input) {
        assertThrows(IllegalArgumentException.class, () -> validation.validate(input));
    }

    @ParameterizedTest
    @CsvSource({"123", "123 + 45"})
    void shouldDoesNotThrowExceptionWhenInputIsValid(String input) {
        assertDoesNotThrow(() -> validation.validate(input));
    }

    @Test
    void shouldReturnIllegalArgumentExceptionWhenInputStringIsEmpty() {
        final String input = "";
        assertThrows(IllegalArgumentException.class, () -> validation.validate(input));
    }
}