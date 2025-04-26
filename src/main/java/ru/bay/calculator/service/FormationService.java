package ru.bay.calculator.service;

import ru.bay.calculator.annotation.Component;

import java.util.Objects;

@Component
public class FormationService {
    public String removeSpaces(String input) {
        if (Objects.isNull(input))
            throw new IllegalArgumentException("Input string cannot be null");
        return input.replaceAll("\\s", "");
    }
}
