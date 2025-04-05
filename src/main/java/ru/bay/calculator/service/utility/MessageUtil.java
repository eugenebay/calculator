package ru.bay.calculator.service.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class MessageUtil {
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
    private static final String GOOD_BYE_MSG = "Exiting the program...";

    private MessageUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Attempt to initialize final class.");
    }

    @SuppressWarnings("java:S106")
    public static void getWelcomeMessage() {
        System.out.println(ANSI_GREEN +
                String.format(WELCOME_MSG,
                        "2.0",
                        System.getProperty("os.name"),
                        getFormattedTime()) +
                ANSI_RESET);
    }

    private static String getFormattedTime() {
        return LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm"));
    }

    @SuppressWarnings("java:S106")
    public static void getGoodByeMessage() {
        System.err.println(GOOD_BYE_MSG);
    }
}
