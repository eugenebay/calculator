package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.model.ParserResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParserService {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(-?\\d+|[IVXLCDM]+)([^0-9IVXLCDM])(\\d+|[IVXLCDM]+)");
    private static final String ROMAN_NUMERAL = "[IVXLCDM]+";

    private final ValidationService validationService;
    private final TranslationService translationService;

    public ParserService(ValidationService validationService, TranslationService translationService) {
        this.validationService = validationService;
        this.translationService = translationService;
    }

    public ParserResult parse(String input) {
        var matcher = getMatcherOrThrow(input);
        String firstMatch = matcher.group(1);
        String operatorMatch = matcher.group(2);
        String secondMatch = matcher.group(3);
        boolean hasTwoRomanNumerals = isRoman(firstMatch) && isRoman(secondMatch);
        return new ParserResult(parseToInteger(firstMatch), operatorMatch, parseToInteger(secondMatch), hasTwoRomanNumerals);
    }

    private Matcher getMatcherOrThrow(String input) {
        var matcher = EXPRESSION_PATTERN.matcher(input);
        if (matcher.find()) {
            return matcher;
        }
        throw new IllegalArgumentException("Invalid expression format");
    }


    private boolean isRoman(String input) {
        return input.matches(ROMAN_NUMERAL);
    }

    private int parseToInteger(String number) {
        if (isRoman(number)) {
            validationService.verifyRomanNumeral(number);
            return translationService.translateToArabic(number);
        }
        return Integer.parseInt(number);
    }
}
