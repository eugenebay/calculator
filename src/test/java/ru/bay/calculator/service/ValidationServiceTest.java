package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfigurationStub;
import ru.bay.calculator.config.ValidationConfigurationStub;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    private ValidationService service;

    @BeforeEach
    void setService() {
        service = new ValidationService(ApplicationConfigurationStub.getInstance(), ValidationConfigurationStub.getInstance());
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {"exit", "wwwExit", "zzzEXIT", "qwerty_exit", "defaultWordExOrExit", "%+5  ...exit"})
    @DisplayName("Should return true when the input string contains the word exit.")
    void shouldReturnTrueWhenTheInputStringContainsTheWordExit(String inputLine) {
        assertTrue(service.isExitCommand(inputLine));
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {"www", "zzzEX_YZ", "qwerty", "defaultWordWithoutEX-IT", "%+5", "..."})
    @DisplayName("Should return false when the input string does not contains the word exit.")
    void shouldReturnFalseWhenTheInputStringDoesNotContainsTheWordExit(String inputLine) {
        assertFalse(service.isExitCommand(inputLine));
    }

    @ParameterizedTest
    @CsvSource({"123", "X+V", "11111111111111", "MMXXIII-MCMLXXXVII"})
    void shouldDoesNotReturnExceptionWhenInputStringIsValid(String input) {
        assertDoesNotThrow(() -> service.validationChain(input));
    }

    @ParameterizedTest
    @CsvSource({"123y", "x+V", "11111111111111q", "Cc - X", "1", "1 ", "111111111111111111111111111111111111111"})
    void shouldReturnIllegalArgumentExceptionWhenInputStringIsNotValid(String input) {
        assertThrows(IllegalArgumentException.class, () -> service.validationChain(input));
    }
}
