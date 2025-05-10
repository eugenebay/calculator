package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.bay.calculator.config.ApplicationConfigStub;
import ru.bay.calculator.config.ValidationConfigStub;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    private static final String EXPECTED_EXPRESSION = "5+5";

    private ValidationService service;

    @BeforeEach
    void setService() {
        service = new ValidationService(ApplicationConfigStub.getInstance(), ValidationConfigStub.getInstance());
    }

    @ParameterizedTest(name = "{0} = {1}")
    @MethodSource("getArgumentsForRemoveSpacesMethod")
    @DisplayName("Should return a string without whitespaces .")
    void shouldReturnStringWithoutSpaces(String expected, String inputString) {
        assertEquals(expected, service.removeSpaces(inputString));
    }

    @ParameterizedTest(name = "{0} = {1}")
    @MethodSource("getComplexArgumentsForRemoveSpacesMethod")
    @DisplayName("Should return a string without whitespaces .")
    void shouldReturnStringWithoutSpacesWithComplexArguments(String expected, String inputString) {
        assertEquals(expected, service.removeSpaces(inputString));
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
    @CsvSource({"123^6", "X+V", "1111111+1111111", "MMXXIII-MCMLXXXVII"})
    void shouldDoesNotReturnExceptionWhenInputStringIsValid(String input) {
        assertDoesNotThrow(() -> service.validationChain(input));
    }

    @ParameterizedTest
    @CsvSource({"123y", "x+V", "11111111111111q", "Cc - X", "1", "1 ", "111111111111111111111111111111111111111"})
    void shouldReturnIllegalArgumentExceptionWhenInputStringIsNotValid(String input) {
        assertThrows(IllegalArgumentException.class, () -> service.validationChain(input));
    }

    @ParameterizedTest
    @CsvSource({"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M", "MMMD", "MMXXI", "MMMCMXCIX"})
    void shouldDoesNotThrowExceptionWhenInputLineIsValidRomanNumeral(String input) {
        assertDoesNotThrow(() -> service.verifyRomanNumeral(input));
    }

    @ParameterizedTest
    @CsvSource({"IIII", "VV", "XXXX", "CCCC", "MMMM", "VX", "LC", "DM", "IC", "XM", "IIV", "XXL", "CCD", "VVVI"})
    void shouldReturnIllegalArgumentExceptionWhenInputLineIsInvalidRomanNumeral(String input) {
        assertThrows(IllegalArgumentException.class, () -> service.verifyRomanNumeral(input));
    }

    private static Stream<Arguments> getArgumentsForRemoveSpacesMethod() {
        return Stream.of(
                Arguments.of(EXPECTED_EXPRESSION, EXPECTED_EXPRESSION),
                Arguments.of(EXPECTED_EXPRESSION, "5 + 5"),
                Arguments.of(EXPECTED_EXPRESSION, "  5   + 5"),
                Arguments.of(EXPECTED_EXPRESSION, "  5+5   "),
                Arguments.of(EXPECTED_EXPRESSION, "        5 +   5          ")
        );
    }

    private static Stream<Arguments> getComplexArgumentsForRemoveSpacesMethod() {
        return Stream.of(
                Arguments.of("-5--5", " - 5 - -5"),
                Arguments.of("-5*X", " -    5  *   X    "),
                Arguments.of("1469/MCDLXIX", "1469   /        MCDLXIX  ")

        );
    }
}
