package ru.bay.calculator.config;

import ru.bay.calculator.validation.*;

import java.util.List;

public class ValidationConfigStub implements ValidationConfig {
    private static boolean isInstanceCreated = false;

    private ValidationConfigStub() {
        // Protection from creation via reflection
        if (isInstanceCreated) throw new IllegalStateException("ValidationConfigStub already initialized.");
        isInstanceCreated = true;
    }

    public static ValidationConfigStub getInstance() {
        return TestValidationConfigurationHolder.INSTANCE;
    }

    @Override
    public List<Validation> getValidations() {
        return List.of(
                new MinAllowedInputLengthValidation(),
                new MaxAllowedInputLengthValidation(),
                new AllowedCharacterValidation(ApplicationConfigStub.getInstance()),
                new ArithmeticOperatorValidation()
        );
    }

    private static class TestValidationConfigurationHolder {
        private static final ValidationConfigStub INSTANCE = new ValidationConfigStub();
    }
}
