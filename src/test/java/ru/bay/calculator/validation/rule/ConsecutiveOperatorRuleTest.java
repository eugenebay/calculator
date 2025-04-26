package ru.bay.calculator.validation.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.data.Cons;

import static org.junit.jupiter.api.Assertions.*;

class ConsecutiveOperatorRuleTest {
    private static final String EXPECTED_RULE_REASON = "Two or more operators in a row";

    private ConsecutiveOperatorRule rule;

    @BeforeEach
    void setRule() {
        rule = new ConsecutiveOperatorRule();
    }

    @ParameterizedTest
    @CsvSource(value = {"1-+1", "1+*1", "1^-1", "1*/1", "X-+V", "V+*V", "I^-C", "M*/C", "1--------", "1++++++++"})
    void shouldReturnNegativeResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Negative result",
                () -> assertTrue(rule.test(input)),
                () -> assertTrue(ruleResult.isRuleBroken()),
                () -> assertEquals(EXPECTED_RULE_REASON, ruleResult.reason())
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"1-1", "1+1", "1*1", "2^2", "12731627-DCCCXLV", "DCCCLXXXVIII-DCCCXLV", "II^XXI"})
    void shouldReturnPositiveResultOfTheArithmeticExpressionRuleCheck(String input) {
        var ruleResult = rule.check(input);
        assertAll("Positive result",
                () -> assertFalse(rule.test(input)),
                () -> assertFalse(ruleResult.isRuleBroken()),
                () -> assertEquals(Cons.EMPTY_RULE_REASON, ruleResult.reason())
        );
    }
}