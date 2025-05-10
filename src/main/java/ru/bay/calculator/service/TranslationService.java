package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.ApplicationConfig;
import ru.bay.calculator.utility.CalculatorUtil;

import java.util.Map;

@Component
public class TranslationService {
    private static final int LOWER_ROMAN_LIMIT = 1;
    private static final int UPPER_ROMAN_LIMIT = 3999;
    private static final int THOUSANDS = 3;
    private static final int HUNDREDS = 2;
    private static final int TENS = 1;
    private static final int ONES = 0;

    private final Map<Character, Integer> romans;
    private final char[] symbols;
    private final int[] digits;

    public TranslationService(ApplicationConfig applicationConfig) {
        this.romans = applicationConfig.getRomans();
        this.symbols = CalculatorUtil.getSymbolsSortedByValueDesc(romans);
        this.digits = CalculatorUtil.getDigitValuesForSymbols(symbols, romans);
    }

    /**
     * Converts a Roman numeral string to its equivalent Arabic (decimal) integer value.
     * The conversion follows standard Roman numeral rules where:
     * - Letters are additive when equal or increasing in value (e.g., VI = 5 + 1 = 6)
     * - A smaller numeral before a larger one indicates subtraction (e.g., IV = 5 - 1 = 4)
     *
     * <p>The method processes the Roman numeral from right to left, applying subtraction logic
     * when a smaller value precedes a larger one. This approach efficiently handles both
     * additive and subtractive notation cases.</p>
     *
     * <p><b>Examples:</b>
     * <ul>
     *   <li>"III" → 3</li>
     *   <li>"IV" → 4</li>
     *   <li>"IX" → 9</li>
     *   <li>"LVIII" → 58</li>
     *   <li>"MCMXCIV" → 1994</li>
     * </ul></p>
     *
     * @param romanNum The Roman numeral string to convert. Must contain only valid Roman
     *                 numeral characters (I, V, X, L, C, D, M) in proper subtractive form.
     *                 Case sensitivity depends on the implementation of the `romans` map.
     * @return The converted Arabic integer value
     */
    public int translateToArabic(String romanNum) {
        final int tail = romanNum.length() - 1;
        int result = romans.get(romanNum.charAt(tail));
        for (int i = tail - 1; i >= 0; i--) {
            if (romans.get(romanNum.charAt(i)) < romans.get(romanNum.charAt(i + 1))) {
                result -= romans.get(romanNum.charAt(i));
            } else {
                result += romans.get(romanNum.charAt(i));
            }
        }
        return result;
    }

    /**
     * Converts an Arabic number to Roman numerals using a subtractive notation approach.
     *
     * <p><b>Core algorithm:</b>
     * <ol>
     *   <li>Validates input range (1-3999)</li>
     *   <li>Processes the number from highest to lowest denomination</li>
     *   <li>Uses two parallel arrays for conversion:
     *     <ul>
     *       <li><code>int[] digits = {1000, 500, 100, 50, 10, 5, 1};</code> ← Arabic values</li>
     *       <li><code>String[] symbols = {"M", "D", "C", "L", "X", "V", "I"};</code> ← Roman symbols</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <p><b>Key steps:</b>
     * 1. For the current number, determine the largest Roman denomination that fits<br>
     * 2. Apply subtractive notation rules when needed (e.g., 900 → "CM")<br>
     * 3. Build the result left-to-right</p>
     */
    public String translateToRoman(int number) {
        checkRomanNumberBounds(number);
        var sb = new StringBuilder();
        while (number > 0) {
            int idx = pointToIndex((int) Math.log10(number));
            if (number >= digits[idx]) {
                number = numberConversionIncluding9xxCases(number, idx, digits, symbols, sb);
            } else {
                number = numberConversionIncluding4xxCases(number, idx, digits, symbols, sb);
            }
        }
        return sb.toString();
    }

    /**
     * Validates that the input number is within convertible range.
     *
     * @param number the number to validate
     * @throws IllegalArgumentException if number is <1 or >3999
     */
    private void checkRomanNumberBounds(int number) {
        if (number < LOWER_ROMAN_LIMIT || number > UPPER_ROMAN_LIMIT) {
            throw new IllegalArgumentException(String.format("Unable to translate %d to roman numeral [not between 1 and 3999]%n", number));
        }
    }

    /**
     * Maps a number's magnitude (power of 10) to the appropriate index in the digits array.
     *
     * <p><b>Conversion logic:</b>
     * <table border="1">
     *   <tr><th>Power</th><th>Magnitude</th><th>digits[] Index</th><th>Denomination</th></tr>
     *   <tr><td>3</td><td>10³ = 1000</td><td>0</td><td>M</td></tr>
     *   <tr><td>2</td><td>10² = 100</td><td>1</td><td>D (500) and C (100)</td></tr>
     *   <tr><td>1</td><td>10¹ = 10</td><td>3</td><td>L (50) and X (10)</td></tr>
     *   <tr><td>0</td><td>10⁰ = 1</td><td>5</td><td>V (5) and I (1)</td></tr>
     * </table>
     *
     * <p>Note the non-linear index mapping to skip even powers (e.g., no direct 10² → index 2 mapping)
     * to accommodate Roman numeral conventions where subtractive pairs are separated by one magnitude.</p>
     *
     * @param power Logarithmic magnitude of the number (log₁₀(number))
     * @return Index in the digits/symbols arrays
     * @throws IllegalArgumentException if power is unsupported
     */
    private int pointToIndex(int power) {
        return switch (power) {
            case THOUSANDS -> 0;
            case HUNDREDS -> 1;
            case TENS -> 3;
            case ONES -> 5;
            default -> throw new IllegalArgumentException(String.format("Unsupported power value - %d", power));
        };
    }

    /**
     * Handles conversion for values requiring subtractive notation (900, 90, 9 cases).
     */
    private int numberConversionIncluding9xxCases(int number, int idx, int[] digits, char[] symbols, StringBuilder sb) {
        if (idx > 0 && idx + 1 < digits.length) {
            int prevIdx = idx - 1;
            int nextIdx = idx + 1;
            int subtractionResult = digits[prevIdx] - digits[nextIdx];
            if (number - subtractionResult >= 0) {
                sb.append(symbols[nextIdx]).append(symbols[prevIdx]);
                return number - subtractionResult;
            }
        }
        sb.append(symbols[idx]);
        return number - digits[idx];
    }

    /**
     * Handles conversion for values requiring subtractive notation (400, 40, 4 cases).
     */
    private int numberConversionIncluding4xxCases(int number, int idx, int[] digits, char[] symbols, StringBuilder sb) {
        int nextIdx = idx + 1;
        int subtractionResult = digits[idx] - digits[nextIdx];
        if (number - subtractionResult >= 0) {
            sb.append(symbols[nextIdx]).append(symbols[idx]);
            return number - subtractionResult;
        } else {
            sb.append(symbols[nextIdx]);
            return number - digits[nextIdx];
        }
    }
}
