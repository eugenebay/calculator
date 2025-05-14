package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.OperationConfig;
import ru.bay.calculator.model.ParserResult;

@Component
public class ComputationService {
    private final OperationConfig operationConfig;
    private final TranslationService translationService;
    private final ParserService parserService;

    public ComputationService(
            OperationConfig operationConfig,
            TranslationService translationService,
            ParserService parserService) {
        this.operationConfig = operationConfig;
        this.translationService = translationService;
        this.parserService = parserService;
    }

    public String compute(String line) {
        final var operations = operationConfig.getOperations();
        final ParserResult parserResult = parserService.parse(line);
        final int answer = operations.get(parserResult.operator())
                .process(parserResult.firstNumber(), parserResult.secondNumber());
        return parserResult.hasTwoRomanNumerals()
                ? translationService.translateToRoman(answer)
                : String.valueOf(answer);
    }
}
