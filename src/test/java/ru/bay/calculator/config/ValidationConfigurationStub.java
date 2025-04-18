package ru.bay.calculator.config;

import ru.bay.calculator.validation.AllowedCharacterValidation;
import ru.bay.calculator.validation.MaxAllowedInputLengthValidation;
import ru.bay.calculator.validation.MinAllowedInputLengthValidation;
import ru.bay.calculator.validation.Validation;

import java.util.List;

public class ValidationConfigurationStub extends ValidationConfiguration {
    private static boolean isInstanceCreated = false;

    private ValidationConfigurationStub() {
        // Protection from creation via reflection
        if (isInstanceCreated) throw new IllegalStateException("ValidationConfiguration already initialized.");
        isInstanceCreated = true;
    }

    public static ValidationConfigurationStub getInstance() {
        return TestValidationConfigurationHolder.INSTANCE;
    }

    @Override
    public List<Validation> getValidations() {
        return List.of(
                new MinAllowedInputLengthValidation(),
                new MaxAllowedInputLengthValidation(),
                new AllowedCharacterValidation(ApplicationConfigurationStub.getInstance())
        );
    }

    private static class TestValidationConfigurationHolder {
        private static final ValidationConfigurationStub INSTANCE = new ValidationConfigurationStub();
    }
}
