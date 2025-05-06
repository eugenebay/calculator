package ru.bay.calculator.config;

import java.util.Map;
import java.util.Set;

public interface ApplicationConfig {
    String getVersion();

    String getQuitWord();

    Map<String, String> getOperators();

    Set<Character> getAllowedCharacters();
}
