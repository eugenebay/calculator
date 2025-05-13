package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfigStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TranslationServiceTest {
    private TranslationService translationService;

    @BeforeEach
    void setTranslator() {
        translationService = new TranslationService(ApplicationConfigStub.getInstance());
    }

    @ParameterizedTest(name = "{1} = {0}")
    @CsvSource(value = {"3,III", "4,IV", "10,X", "21,XXI", "121,CXXI", "354,CCCLIV", "888,DCCCLXXXVIII", "845,DCCCXLV",
            "14,XIV", "79,LXXIX", "225,CCXXV", "1469,MCDLXIX", "2022,MMXXII", "2025,MMXXV", "3789,MMMDCCLXXXIX"})
    void shouldReturnConvertedRomanNumeralToArabicNumeral(int arabicNum, String romanNum) {
        assertEquals(arabicNum, translationService.translateToArabic(romanNum));
    }

    @ParameterizedTest(name = "{1} = {0}")
    @CsvSource(value = {"III,3", "IV,4", "X,10", "XXI,21", "CXXI,121", "CCCLIV,354", "DCCCLXXXVIII,888", "DCCCXLV,845",
            "XIV,14", "LXXIX,79", "CCXXV,225", "MCDLXIX,1469", "MMXXII,2022", "MMXXV,2025", "MMMDCCLXXXIX,3789"})
    void shouldReturnConvertedArabicNumeralToRomanNumeral(String romanNum, int arabicNum) {
        assertEquals(romanNum, translationService.translateToRoman(arabicNum));
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "-1", "4000", "3243223"})
    void shouldReturnIllegalArgumentExceptionWhenCannotTranslateToRomanNumeral(int arabicNum) {
        assertThrows(IllegalArgumentException.class, () -> translationService.translateToRoman(arabicNum));
    }
}