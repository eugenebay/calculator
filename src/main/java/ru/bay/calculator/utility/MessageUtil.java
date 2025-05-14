package ru.bay.calculator.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtil {
    public static final String WELCOME_MSG = """
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
    public static final String GOOD_BYE_MSG = "Exiting the program. [Session duration - %s]%n";
}
