package ru.bay.calculator.service.util;

public final class MessageUtils {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String WELCOME_MSG = """
                                ████                      ████             █████                     \s
                               ░░███                     ░░███            ░░███                      \s
              ██████   ██████   ░███   ██████  █████ ████ ░███   ██████   ███████    ██████  ████████\s
             ███░░███ ░░░░░███  ░███  ███░░███░░███ ░███  ░███  ░░░░░███ ░░░███░    ███░░███░░███░░███
            ░███ ░░░   ███████  ░███ ░███ ░░░  ░███ ░███  ░███   ███████   ░███    ░███ ░███ ░███ ░░░\s
            ░███  ███ ███░░███  ░███ ░███  ███ ░███ ░███  ░███  ███░░███   ░███ ███░███ ░███ ░███    \s
            ░░██████ ░░████████ █████░░██████  ░░████████ █████░░████████  ░░█████ ░░██████  █████   \s
            """;
    private static final String GOOD_BYE_MSG = "Exiting the program...";

    private MessageUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Attempt to initialize final class.");
    }

    public static void getWelcomeMessage() {
        System.out.println(ANSI_GREEN + WELCOME_MSG + ANSI_RESET);
    }

    public static void getGoodByeMessage() {
        System.err.println(GOOD_BYE_MSG);
    }
}
