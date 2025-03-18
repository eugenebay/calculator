package ru.bay.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubOperationTest {
    private SubOperation operation;

    @BeforeEach
    void setOperation() {
        operation = new SubOperation();
    }

    @DisplayName("Should return the result of subtracting two numbers.")
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource(value = {"5,5,0", "10,5,5", "48514,5666,42848", "-2,2,-4", "-25,5,-30", "4451254,44585225,-40133971"})
    void shouldReturnTheResultOfSubtractingTwoNumbers(int firstNum, int secondNum, int expectedResult) {
        assertEquals(expectedResult, operation.process(firstNum, secondNum));
    }

    @Test
    @DisplayName("Should return an exception when an integer overflow occurs.")
    void shouldReturnAnArithmeticExceptionWhenAnIntegerOverflowOccurs() {
        final var expectedExceptionMessage = "integer overflow";
        ArithmeticException ae = assertThrows(ArithmeticException.class, () -> operation.process(Integer.MIN_VALUE, 1));
        assertEquals(expectedExceptionMessage, ae.getMessage());
    }
}