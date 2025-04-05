package ru.bay.calculator.service;


import lombok.extern.slf4j.Slf4j;
import ru.bay.calculator.context.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static ru.bay.calculator.service.utility.CalculatorUtil.newBufferedReaderInstance;
import static ru.bay.calculator.service.utility.CalculatorUtil.outputToConsole;
import static ru.bay.calculator.service.utility.MessageUtil.getGoodByeMessage;
import static ru.bay.calculator.service.utility.MessageUtil.getWelcomeMessage;

@Slf4j
@Component
public class Application {
    private final FormationService formationService;
    private final ValidationService validationService;

    public Application(FormationService formationService, ValidationService validationService) {
        this.formationService = formationService;
        this.validationService = validationService;
    }

    public void run() {
        getWelcomeMessage();
        try (var br = newBufferedReaderInstance()) {
            handleConsoleInput(br);
        } catch (IOException ex) {
            log.error("An exception occurred while reading input - {}", ex.getMessage());
        }
    }

    private void handleConsoleInput(BufferedReader br) throws IOException {
        String input;
        while (Objects.nonNull(input = br.readLine())) {
            var trimmedInput = formationService.removeSpaces(input);
            if (validationService.isExitCommand(trimmedInput)) {
                getGoodByeMessage();
                break;
            }
            outputToConsole(trimmedInput);
        }
    }
}
