package ru.bay.calculator.validation.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.data.Cons;

import static org.junit.jupiter.api.Assertions.*;

class TrailingOperatorRuleTest {
    private static final String EXPECTED_RULE_REASON = "Operator not allowed at end of expression";

    private TrailingOperatorRule rule;

    @BeforeEach
    void setRule() {
        rule = new TrailingOperatorRule();
    }

    @ParameterizedTest
    @CsvSource(value = {"1+1-", "X+V-", "2^5*", "10/", "IV+", "5+", "1//", "1^^"})
    void shouldReturnNegativeResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Negative result",
                () -> assertTrue(rule.test(input)),
                () -> assertTrue(ruleResult.isRuleBroken()),
                () -> assertEquals(EXPECTED_RULE_REASON, ruleResult.reason())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"1+1", "X-V", "5*10", "IV/II", "2^5"})
    void shouldReturnPositiveResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Positive result",
                () -> assertFalse(rule.test(input)),
                () -> assertFalse(ruleResult.isRuleBroken()),
                () -> assertEquals(Cons.EMPTY_RULE_REASON, ruleResult.reason())
        );
    }
}