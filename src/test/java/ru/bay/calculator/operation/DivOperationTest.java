package ru.bay.calculator.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivOperationTest {
    private DivOperation operation;

    @BeforeEach
    void setOperation() {
        operation = new DivOperation();
    }

    @DisplayName("Should return the result of dividing two numbers.")
    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource(value = {"25,5,5", "125,25,5", "3600,60,60", "4568,359,12", "-25,5,-5", "1457846,454895,3", "1127,-6,-187"})
    void shouldReturnTheResultOfDividingTwoNumbers(int firstNum, int secondNum, int expectedResult) {
        assertEquals(expectedResult, operation.process(firstNum, secondNum));
    }

    @Test
    @DisplayName("Should return an exception when a number is divided by zero.")
    void shouldReturnAnArithmeticExceptionWhenANumberDividedByZero() {
        final var expectedExceptionMessage = "/ by zero";
        ArithmeticException ae = assertThrows(ArithmeticException.class, () -> operation.process(5, 0));
        assertEquals(expectedExceptionMessage, ae.getMessage());
    }
}
