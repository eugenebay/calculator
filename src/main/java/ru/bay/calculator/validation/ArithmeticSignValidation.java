package ru.bay.calculator.validation;

import ru.bay.calculator.context.ObjectFactory;
import ru.bay.calculator.utility.CalculatorUtil;
import ru.bay.calculator.validation.rule.Rule;

import java.util.List;

public class ArithmeticSignValidation implements Validation {
    private static final String RULE_PATH = "src/main/java/ru/bay/calculator/validation/rule";

    private final List<Rule> rules;

    public ArithmeticSignValidation() {
        this.rules = populateRules();
    }

    @Override
    public void validate(String input) throws IllegalArgumentException {
        rules.forEach(rule -> {
            var ruleResult = rule.check(input);
            if (ruleResult.isRuleBroken()) throw new IllegalArgumentException(ruleResult.reason());
        });
    }

    private List<Rule> populateRules() {
        return ObjectFactory.GET.findByCondition(
                        RULE_PATH,
                        clazz -> CalculatorUtil.isNotInterfaceAndNotDisabled(clazz) && Rule.class.isAssignableFrom(clazz))
                .stream()
                .map(clazz -> (Rule) ObjectFactory.GET.createObject(clazz))
                .toList();
    }
}
