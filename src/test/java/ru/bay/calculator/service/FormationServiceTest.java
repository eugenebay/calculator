package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormationServiceTest {
    private static final String EXPECTED_EXPRESSION = "5+5";

    private FormationService service;

    @BeforeEach
    void setService() {
        service = new FormationService();
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
