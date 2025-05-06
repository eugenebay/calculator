package ru.bay.calculator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.SneakyThrows;
import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.property.ApplicationProperties;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Getter
public class ApplicationConfiguration {
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "application.yml";
    private final String version;
    private final String quitWord;
    private final Set<Character> allowedCharacters;

    public ApplicationConfiguration() {
        final ApplicationProperties properties = newPropertiesInstance();
        this.version = properties.getVersion();
        this.quitWord = properties.getQuitWord();
        this.allowedCharacters = populateAllowedCharacters(properties);
    }

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
