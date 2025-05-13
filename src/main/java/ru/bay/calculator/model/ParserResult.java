package ru.bay.calculator.model;

public record ParserResult(int firstNum, String operator, int secondNum, boolean hasTwoRomanNumerals) {
}
