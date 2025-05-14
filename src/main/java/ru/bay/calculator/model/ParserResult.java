package ru.bay.calculator.model;

public record ParserResult(int firstNumber, String operator, int secondNumber, boolean hasTwoRomanNumerals) {
}
