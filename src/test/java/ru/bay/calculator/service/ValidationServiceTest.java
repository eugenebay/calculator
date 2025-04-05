package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfig;
import ru.bay.calculator.config.ApplicationConfigStub;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationServiceTest {
    private final ApplicationConfig config = new ApplicationConfigStub();

    private ValidationService service;

    @BeforeEach
    void setService() {
        service = new ValidationService(config);
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
}
