package ru.bay.calculator.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleMathExpressionValidatorTest {
    private SimpleMathExpressionValidator validator;

    @BeforeEach
    void setValidator() {
        validator = new SimpleMathExpressionValidator();
    }

    @ParameterizedTest
    @CsvSource({"1+1", "V*X", "MMM+DCCL", "X^5", "13762513+21371628", "123123/3123", "MMMDCCLXXXIX-3210"})
    void shouldDoesNotReturnExceptionWhenTheInputStringMatchesTwoDigitArithmeticExpression(String input) {
        assertDoesNotThrow(() -> validator.validate(input));
    }

    @ParameterizedTest
    @CsvSource({"1+2-3", "X/V+II", "10*2-5+3", "C-L+X-V", "5^2*3+10-1", "5^II*3+X"})
    void shouldReturnIllegalArgumentExceptionWhenTheInputStringMatchesSeveralDigitArithmeticExpression(String input) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(input));
    }
}