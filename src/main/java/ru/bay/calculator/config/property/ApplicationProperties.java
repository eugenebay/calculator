package ru.bay.calculator.config.property;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ApplicationProperties {
    private String chars;
    private Map<String, String> operators;
    private Map<Character, Integer> romans;
}
