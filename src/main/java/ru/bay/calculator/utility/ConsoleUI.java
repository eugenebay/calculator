package ru.bay.calculator.utility;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public final class ConsoleUI {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String WELCOME_MSG = """
                 ______
                /_____/\\
               /_____\\\\ \\
              /_____\\ \\\\ /\\
             /_____/ \\/ / /\\
            /_____/ /   \\//\\\\
            \\_____\\//\\   / /
             \\_____/ / /\\ /\t\tCalculator v%s
              \\_____/ \\\\ \\\t\tCopyright (c) 2025 Eugene BAY
               \\_____\\ \\\\\t\tMIT License.
                \\_____\\/\t\t%s, %s
            """;
    private static final String GOOD_BYE_MSG = "Exiting the program. [Session duration - %s s.]%n";
    @Setter
    @Getter
    private static LocalDateTime startOfSession;

    private ConsoleUI() throws IllegalAccessException {
        throw new IllegalAccessException("Attempt to initialize final class.");
    }

    // Suppress SonarQube console output warning.
    @SuppressWarnings("java:S106")
    public static void displayWelcomeMessage() {
        setStartOfSession(LocalDateTime.now(ZoneId.systemDefault()));
        System.out.println(ANSI_GREEN + String.format(
                WELCOME_MSG,
                "2.3",
                System.getProperty("os.name"),
                getFormattedTime(getStartOfSession())
        ) + ANSI_RESET);
    }

    private static String getFormattedTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm"));
    }

    @SuppressWarnings("java:S106")
    public static void displayGoodByeMessage() {
        System.err.printf(GOOD_BYE_MSG, Duration.between(getStartOfSession(), LocalDateTime.now(ZoneId.systemDefault()))
                .getSeconds());
    }

    @SuppressWarnings("java:S106")
    public static void displayResult(String line) {
        System.out.println(line);
    }

    @SuppressWarnings("java:S106")
    public static void displayExceptionMessage(Exception ex) {
        System.err.println(ex.getMessage());
    }

    public static void handleIOException(IOException ex) {
        log.error("An exception occurred while reading input - {}", ex.getMessage());
    }
}
