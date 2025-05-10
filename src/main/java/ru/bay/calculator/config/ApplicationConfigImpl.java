package ru.bay.calculator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.property.ApplicationProperties;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationConfigImpl implements ApplicationConfig {
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "application.yml";

    private final ApplicationProperties properties;
    private final Set<Character> allowedCharacters;

    public ApplicationConfigImpl() {
        this.properties = newPropertiesInstance();
        this.allowedCharacters = populateAllowedCharacters(properties);
    }

    @Override
    public String getVersion() {
        return properties.getVersion();
    }

    @Override
    public String getQuitWord() {
        return properties.getQuitWord();
    }

    @Override
    public Map<String, String> getOperators() {
        return Map.copyOf(properties.getOperators());
    }

    @Override
    public Map<Character, Integer> getRomans() {
        return Map.copyOf(properties.getRomans());
    }

    @Override
    public Set<Character> getAllowedCharacters() {
        return Set.copyOf(allowedCharacters);
    }

    @SneakyThrows
    private ApplicationProperties newPropertiesInstance() {
        var objectMapper = new ObjectMapper(new YAMLFactory());
        try (var inputStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE_NAME)) {
            return objectMapper.readValue(inputStream, ApplicationProperties.class);
        }
    }

    private Set<Character> populateAllowedCharacters(ApplicationProperties properties) {
        return properties.getChars()
                .chars()
                .mapToObj(integerRepresentation -> (char) integerRepresentation)
                .collect(Collectors.toSet());
    }
}
