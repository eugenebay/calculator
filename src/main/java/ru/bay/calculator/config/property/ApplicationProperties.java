package ru.bay.calculator.config.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ApplicationProperties {
    private String version;
    @JsonProperty("quit-word")
    private String quitWord;
    private String chars;
    private Map<String, String> operators;
    private Map<Character, Integer> romans;
}
