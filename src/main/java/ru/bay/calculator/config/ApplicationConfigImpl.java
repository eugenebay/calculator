package ru.bay.calculator.config;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.bay.calculator.config.property.ApplicationProperties;
import ru.bay.calculator.context.Component;
import ru.bay.calculator.validation.Validation;

import java.util.List;

@Component
public class ApplicationConfigImpl implements ApplicationConfig {
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "application.yml";


    public ApplicationConfigImpl() {
        final var props = newPropertiesInstance();
        // use props
    }

    private ApplicationProperties newPropertiesInstance() {
        final var yaml = new Yaml(new Constructor(ApplicationProperties.class, new LoaderOptions()));
        final var inputStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE_NAME);
        return yaml.load(inputStream);
    }

    @Override
    public List<Validation> getValidators() {
        return List.of();
    }
}
