package ru.bay.calculator.validation.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.data.Cons;

import static org.junit.jupiter.api.Assertions.*;

class LeadingOperatorRuleTest {
    private static final String EXPECTED_RULE_REASON = "Invalid character at beginning of expression";

    private LeadingOperatorRule rule;

    @BeforeEach
    void setRule() {
        rule = new LeadingOperatorRule();
    }

    @ParameterizedTest
    @CsvSource(value = {"-X+V", "*5+3", "^1+2", "--1+1", "+X-II", "/10*2", "++1-1", "-+5*3", "+5-3", "^^1", "+1+"})
    void shouldReturnNegativeResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Negative result",
                () -> assertTrue(rule.test(input)),
                () -> assertTrue(ruleResult.isRuleBroken()),
                () -> assertEquals(EXPECTED_RULE_REASON, ruleResult.reason())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"-1+1", "X+V", "IV-X", "5-3", "100/50"})
    void shouldReturnPositiveResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Positive result",
                () -> assertFalse(rule.test(input)),
                () -> assertFalse(ruleResult.isRuleBroken()),
                () -> assertEquals(Cons.EMPTY_RULE_REASON, ruleResult.reason())
        );
    }
}