package ru.bay.calculator.validation.rule;

public record RuleResult(boolean isRuleBroken, String reason) {
    public static RuleResult positiveResult() {
        return new RuleResult(false, "");
    }

    public static RuleResult negativeResult(String reason) {
        return new RuleResult(true, reason);
    }
}
