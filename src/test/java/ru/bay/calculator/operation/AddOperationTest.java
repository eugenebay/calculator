package ru.bay.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddOperationTest {
    private AddOperation operation;

    @BeforeEach
    void setOperation() {
        operation = new AddOperation();
    }

    @DisplayName("Should return the result of adding two numbers.")
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource(value = {"2,2,4", "25,5,30", "12458,4586,17044", "-2,2,0", "-25,5,-20", "789,485465,486254"})
    void shouldReturnTheResultOfAddingTwoNumbers(int firstNum, int secondNum, int expectedResult) {
        assertEquals(expectedResult, operation.process(firstNum, secondNum));
    }

    @Test
    @DisplayName("Should return an exception when an integer overflow occurs.")
    void shouldReturnAnArithmeticExceptionWhenAnIntegerOverflowOccurs() {
        final var expectedExceptionMessage = "integer overflow";
        ArithmeticException ae = assertThrows(ArithmeticException.class, () -> operation.process(Integer.MAX_VALUE, 1));
        assertEquals(expectedExceptionMessage, ae.getMessage());
    }
}