package ru.bay.calculator.config;

import java.util.Map;
import java.util.Set;

public interface ApplicationConfig {
    String getVersion();

    String getQuitWord();

    Map<String, String> getOperators();

    Map<Character, Integer> getRomans();

    Set<Character> getAllowedCharacters();
}
