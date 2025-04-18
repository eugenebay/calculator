package ru.bay.calculator.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfigurationStub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllowedCharacterValidationTest {
    private AllowedCharacterValidation validation;

    @BeforeEach
    void setValidation() {
        validation = new AllowedCharacterValidation(ApplicationConfigurationStub.getInstance());
    }

    @ParameterizedTest
    @CsvSource({"0", "1", "123", "123+123", "123+IV", "X-V"})
    void shouldDoesNotReturnExceptionWhenInputStringContainsAllowedCharacters(String input) {
        assertDoesNotThrow(() -> validation.validate(input));
    }

    @ParameterizedTest
    @CsvSource({"z", "u", "11111y", "IVXy", "5+f"})
    void shouldReturnIllegalArgumentExceptionWhenInputStringDoesNotContainsAllowedCharacters(String input) {
        assertThrows(IllegalArgumentException.class, () -> validation.validate(input));
    }

    @ParameterizedTest
    @CsvSource({"i", "v", "x", "lc", "5+md"})
    void shouldReturnIllegalArgumentExceptionWhenInputStringContainAllowedCharacterButInLowercase(String caseSensitiveInput) {
        assertThrows(IllegalArgumentException.class, () -> validation.validate(caseSensitiveInput));
    }

    @Test
    void shouldReturnIllegalArgumentExceptionWhenInputStringContainWhitespace() {
        final String inputWithWhitespace = "5+ X";
        assertThrows(IllegalArgumentException.class, () -> validation.validate(inputWithWhitespace));
    }
}
