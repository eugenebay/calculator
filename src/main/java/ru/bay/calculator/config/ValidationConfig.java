package ru.bay.calculator.config;

import ru.bay.calculator.validation.Validation;

import java.util.List;

public interface ValidationConfig {
    List<Validation> getValidations();
}
