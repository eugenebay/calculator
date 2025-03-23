package ru.bay.calculator.service;

import java.util.Objects;

public class FormationService {
    public String removeSpaces(String input) {
        if (Objects.isNull(input))
            throw new IllegalArgumentException("Input string cannot be null");
        return input.replaceAll("\\s", "");
    }
}
