package ru.bay.calculator.config;

import ru.bay.calculator.context.Component;
import ru.bay.calculator.context.ObjectFactory;
import ru.bay.calculator.service.utility.CalculatorUtil;
import ru.bay.calculator.validation.Validation;

import java.util.List;

@Component
public class ValidationConfiguration {
    private static final String VALIDATION_PATH = "src/main/java/ru/bay/calculator/validation";

    private final List<Validation> validations;

    public ValidationConfiguration() {
        this.validations = populateValidations();
    }

    public List<Validation> getValidations() {
        return List.copyOf(validations);
    }

    private List<Validation> populateValidations() {
        var classes = ObjectFactory.GET.findByCondition(
                VALIDATION_PATH,
                clazz -> CalculatorUtil.isNotInterface(clazz) && Validation.class.isAssignableFrom(clazz)
        );
        return classes.stream()
                .map(clazz -> (Validation) ObjectFactory.GET.createObject(clazz))
                .toList();
    }
}
