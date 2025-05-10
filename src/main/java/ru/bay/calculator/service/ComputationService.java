package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.OperationConfig;

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
        return line;
    }
}
