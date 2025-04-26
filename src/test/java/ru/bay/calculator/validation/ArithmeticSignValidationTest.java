package ru.bay.calculator.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithmeticSignValidationTest {
    private ArithmeticSignValidation validation;

    @BeforeEach
    void setValidation() {
        validation = new ArithmeticSignValidation();
    }

    @ParameterizedTest
    @CsvSource({"*1-1", "+1+1", "1++++++++", "1++1", "-1-1-", "1--------", "--1-1", "1--1", "-1--1", "1+-1", "1+*1",
            "-X+V", "X*V-"
    })
    void shouldThrowIllegalArgumentExceptionWhenThereAreSeveralArithmeticSignsInALine(String input) {
        assertThrows(IllegalArgumentException.class, () -> validation.validate(input));
    }

    @ParameterizedTest
    @CsvSource({"-1*1", "1-1", "1+1", "1*1", "1/1", "1^2", "-1-1", "123+12312313", "12731627-378647", "-12831*1273612",
            "X+V", "X+0", "12731627-DCCCXLV", "DCCCLXXXVIII-DCCCXLV", "II^XXI"
    })
    void shouldDoesNotThrowExceptionIfTheLineHasTheCorrectNumberOfArithmeticSigns(String input) {
        assertDoesNotThrow(() -> validation.validate(input));
    }
}
