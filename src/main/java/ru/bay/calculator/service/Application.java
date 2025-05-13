package ru.bay.calculator.service;


import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.ui.ConsoleUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static ru.bay.calculator.utility.CalculatorUtil.newBufferedReaderInstance;

@Component
public class Application {
    private final ConsoleUI consoleUI;
    private final ValidationService validationService;
    private final ComputationService computationService;

    public Application(
            ConsoleUI consoleUI,
            ValidationService validationService,
            ComputationService computationService
    ) {
        this.consoleUI = consoleUI;
        this.validationService = validationService;
        this.computationService = computationService;
    }

    public void run() {
        consoleUI.displayWelcomeMessage();
        runSession();
    }

    private void runSession() {
        try (var br = newBufferedReaderInstance()) {
            readInputLine(br);
        } catch (IOException ex) {
            consoleUI.handleException(ex);
        }
    }

    private void readInputLine(BufferedReader br) throws IOException {
        String input;
        while (Objects.nonNull(input = br.readLine())) {
            var trimmedInput = validationService.removeSpaces(input);
            if (shouldTerminate(trimmedInput)) break;
            validateAndCompute(trimmedInput);
        }
    }

    private boolean shouldTerminate(String input) {
        if (validationService.isExitCommand(input)) {
            consoleUI.displayGoodByeMessage();
            return true;
        }
        return false;
    }

    private void validateAndCompute(String input) {
        try {
            validationService.validationChain(input);
            consoleUI.displayResult(computationService.compute(input));
        } catch (IllegalArgumentException | ArithmeticException ex) {
            consoleUI.displayExceptionMessage(ex);
        }
    }
}
