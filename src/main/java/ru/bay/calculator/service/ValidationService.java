package ru.bay.calculator.service;

import java.util.Objects;

public class ValidationService {
    private static final String EXIT = "exit";

    public boolean isExitCommand(String input) {
        if (Objects.isNull(input))
            return false;
        return input.toLowerCase().contains(EXIT);
    }
}
