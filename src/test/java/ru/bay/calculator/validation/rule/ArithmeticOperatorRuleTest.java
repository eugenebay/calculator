package ru.bay.calculator.validation.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.data.Cons;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperatorRuleTest {
    private static final String EXPECTED_RULE_REASON = "There are no arithmetic operators";

    private ArithmeticOperatorRule rule;

    @BeforeEach
    void setRule() {
        rule = new ArithmeticOperatorRule();
    }

    @ParameterizedTest
    @CsvSource(value = {"X", "12312321312", "213213123XVIII", "MMMDCCLXXXIX", "100500", "IVXLCDM", "0", "C", "999"})
    void shouldReturnNegativeResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Negative result",
                () -> assertTrue(rule.test(input)),
                () -> assertTrue(ruleResult.isRuleBroken()),
                () -> assertEquals(EXPECTED_RULE_REASON, ruleResult.reason())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"1+1", "X-V", "10*20", "C/L", "5^3", "456+789", "MMM+DCCL", "1*2", "XV^3"})
    void shouldReturnPositiveResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Positive result",
                () -> assertFalse(rule.test(input)),
                () -> assertFalse(ruleResult.isRuleBroken()),
                () -> assertEquals(Cons.EMPTY_RULE_REASON, ruleResult.reason())
        );
    }
}