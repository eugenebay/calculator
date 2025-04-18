package ru.bay.calculator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;
import ru.bay.calculator.config.property.ApplicationProperties;
import ru.bay.calculator.config.property.ApplicationPropertiesMapper;
import ru.bay.calculator.context.Component;

@Component
public class ApplicationConfiguration {
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "application.yml";

    private final ApplicationProperties properties;

    public ApplicationConfiguration() {
        this.properties = newPropertiesInstance();
    }

    public ApplicationProperties getProperties() {
        return ApplicationPropertiesMapper.INSTANCE.copy(properties);
    }

    @SneakyThrows
    private ApplicationProperties newPropertiesInstance() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (var inputStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE_NAME)) {
            return mapper.readValue(inputStream, ApplicationProperties.class);
        }
    }
}
