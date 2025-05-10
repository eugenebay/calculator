package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.model.ParserResult;

@Component
public class ParserService {
    private final ValidationService validationService;

    public ParserService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public ParserResult parse(String input) {
        return new ParserResult();
    }
}
