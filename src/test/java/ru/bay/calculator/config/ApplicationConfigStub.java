package ru.bay.calculator.config;

import ru.bay.calculator.validation.Validation;

import java.util.List;

public class ApplicationConfigStub implements ApplicationConfig {
    @Override
    public List<Validation> getValidators() {
        return List.of();
    }
}
