package ru.bay.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PowOperationTest {
    private PowOperation operation;

    @BeforeEach
    void setOperation() {
        operation = new PowOperation();
    }

    @ParameterizedTest(name = "{0} ^ {1} = {2}")
    @CsvSource(value = {"5,3,125", "16,6,16777216", "5,6,15625"})
    @DisplayName("Should return the result of raising the number to the given power.")
    void shouldReturnTheResultOfRaisingTheNumberToTheGivenPower(int number, int power, int expectedResult) {
        assertEquals(expectedResult, operation.process(number, power));
    }

    @Test
    @DisplayName("Should return an exception when the power of a number exceeds the value of an integer.")
    void shouldReturnAnArithmeticExceptionWhenThePowerOfNumberExceedsTheMaxIntegerValue() {
        final var expectedExceptionMessage = "integer overflow";
        ArithmeticException ae = assertThrows(ArithmeticException.class, () -> operation.process(125, 5));
        assertEquals(expectedExceptionMessage, ae.getMessage());
    }

    @Test
    @DisplayName("Should return an exception if the power is less than or equal to zero.")
    void shouldReturnAnArithmeticExceptionWhenThePowerIsLessOrEqualToZero() {
        final var expectedExceptionMessage = "the power must be greater than zero";
        var exceptionWithPowerEqualToZero = assertThrows(ArithmeticException.class, () -> operation.process(125, 0));
        var exceptionWithPowerLessThanZero = assertThrows(ArithmeticException.class, () -> operation.process(125, -1));
        assertAll("Testing a few ArithmeticException",
                () -> assertEquals(expectedExceptionMessage, exceptionWithPowerEqualToZero.getMessage()),
                () -> assertEquals(expectedExceptionMessage, exceptionWithPowerLessThanZero.getMessage())
        );
    }
}