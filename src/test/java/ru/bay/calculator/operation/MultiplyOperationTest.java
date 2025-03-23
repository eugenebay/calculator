package ru.bay.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MultiplyOperationTest {
    private MultiplyOperation operation;

    @BeforeEach
    void setOperation() {
        operation = new MultiplyOperation();
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource(value = {"2,2,4", "12,5,60", "60,60,3600", "-2,2,-4", "-25,5,-125", "3600,15,54000"})
    @DisplayName("Should return the result of multiplying two numbers.")
    void shouldReturnTheResultOfMultiplyingTwoNumbers(int firstNum, int secondNum, int expectedResult) {
        assertEquals(expectedResult, operation.process(firstNum, secondNum));
    }

    @Test
    @DisplayName("Should return an exception if the result of the calculation exceeds the value of the integer.")
    void shouldReturnArithmeticExceptionIfResultCalculationExceedsInteger() {
        final var expectedExceptionMessage = "integer overflow";
        ArithmeticException ae = assertThrows(ArithmeticException.class, () -> operation.process(4589558, 469));
        assertEquals(expectedExceptionMessage, ae.getMessage());
    }
}