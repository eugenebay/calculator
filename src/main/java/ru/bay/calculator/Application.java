package ru.bay.calculator;


import ru.bay.calculator.config.ApplicationConfigImpl;
import ru.bay.calculator.service.FormationService;
import ru.bay.calculator.service.ValidationService;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.bay.calculator.service.util.CalculatorUtils.newBufferedReaderInstance;
import static ru.bay.calculator.service.util.MessageUtils.getGoodByeMessage;
import static ru.bay.calculator.service.util.MessageUtils.getWelcomeMessage;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getSimpleName());

    private final FormationService formationService;
    private final ValidationService validationService;

    public Application() {
        ApplicationFactory.FACTORY.getObject(ApplicationConfigImpl.class);
        this.formationService = ApplicationFactory.FACTORY.getObject(FormationService.class);
        this.validationService = ApplicationFactory.FACTORY.getObject(ValidationService.class);
    }

    public void run() {
        getWelcomeMessage();
        try (var br = newBufferedReaderInstance()) {
            String input;
            while (Objects.nonNull(input = br.readLine())) {
                var processedInput = formationService.removeSpaces(input);
                if (validationService.isExitCommand(processedInput)) {
                    getGoodByeMessage();
                    break;
                }
                System.out.println(processedInput);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "An exception occurred while reading input.", ex);
        }
    }
}
