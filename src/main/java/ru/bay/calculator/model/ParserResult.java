package ru.bay.calculator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParserResult {
    private String operator;
    private int firstNum;
    private int secondNum;
    private int romanNumeralsCounter;

    public boolean hasTwoRomanNumerals() {
        return romanNumeralsCounter == 2;
    }
}
