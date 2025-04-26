package ru.bay.calculator.validation.rule;

import java.util.function.Predicate;

public interface Rule extends Predicate<String> {
    default RuleResult check(String input) {
        return test(input)
                ? RuleResult.negativeResult(getReason())
                : RuleResult.positiveResult();
    }

    String getReason();
}
