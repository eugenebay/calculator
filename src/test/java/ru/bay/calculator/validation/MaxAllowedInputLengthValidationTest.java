package ru.bay.calculator.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MaxAllowedInputLengthValidationTest {
    private MaxAllowedInputLengthValidation validation;

    @BeforeEach
    void setValidation() {
        validation = new MaxAllowedInputLengthValidation();
    }

    @ParameterizedTest
    @MethodSource("getArgumentsWithGeneratedLength")
    void shouldDoesNotReturnExceptionWhenInputStringLessThanMaxAllowedInputLength(String input) {
        assertDoesNotThrow(() -> validation.validate(input));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsWithMaxGeneratedLength")
    void shouldReturnIllegalArgumentExceptionWhenInputStringIsMaxAllowedInputLength(String input) {
        assertThrows(IllegalArgumentException.class, () -> validation.validate(input));
    }

    private static Stream<Arguments> getArgumentsWithGeneratedLength() {
        return Stream.of(
                Arguments.of(createStringWithFixedLength(3)),
                Arguments.of(createStringWithFixedLength(15)),
                Arguments.of(createStringWithFixedLength(23))
        );
    }

    private static Stream<Arguments> getArgumentsWithMaxGeneratedLength() {
        return Stream.of(
                Arguments.of(createStringWithFixedLength(24)),
                Arguments.of(createStringWithFixedLength(30)),
                Arguments.of(createStringWithFixedLength(100))
        );
    }

    private static String createStringWithFixedLength(int len) {
        final String CON = "1";
        return CON.repeat(Math.max(0, len));
    }
}