package ru.bay.calculator.service;


import lombok.extern.slf4j.Slf4j;
import ru.bay.calculator.context.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static ru.bay.calculator.service.utility.CalculatorUtil.newBufferedReaderInstance;
import static ru.bay.calculator.service.utility.ConsoleUI.*;

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
        displayWelcomeMessage();
        runSession();
    }

    private void runSession() {
        try (var br = newBufferedReaderInstance()) {
            readInputLine(br);
        } catch (IOException ex) {
            handleIOException(ex);
        }
    }

    private void readInputLine(BufferedReader br) throws IOException {
        String input;
        while (Objects.nonNull(input = br.readLine())) {
            var trimmedInput = formationService.removeSpaces(input);
            if (shouldTerminate(trimmedInput)) break;
            validateAndCompute(trimmedInput);
        }
    }

    private boolean shouldTerminate(String input) {
        if (validationService.isExitCommand(input)) {
            displayGoodByeMessage();
            return true;
        }
        return false;
    }

    private void validateAndCompute(String input) {
        try {
            validationService.validationChain(input);
            displayResult(input);
        } catch (IllegalArgumentException | ArithmeticException ex) {
            displayExceptionMessage(ex);
        }
    }
}
