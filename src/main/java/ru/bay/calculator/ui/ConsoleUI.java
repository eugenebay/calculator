package ru.bay.calculator.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.config.ApplicationConfig;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static ru.bay.calculator.utility.ColorUtil.*;
import static ru.bay.calculator.utility.MessageUtil.GOOD_BYE_MSG;
import static ru.bay.calculator.utility.MessageUtil.WELCOME_MSG;

@Slf4j
@Component
public class ConsoleUI {
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int SECONDS_PER_HOUR = 3600;

    private final ApplicationConfig applicationConfig;
    @Setter
    @Getter
    private LocalDateTime startOfSession;

    public ConsoleUI(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    // Suppress SonarQube console output warning.
    @SuppressWarnings("java:S106")
    public void displayWelcomeMessage() {
        setStartOfSession(LocalDateTime.now(ZoneId.systemDefault()));
        System.out.println(GREEN + String.format(
                WELCOME_MSG,
                applicationConfig.getVersion(),
                System.getProperty("os.name"),
                getFormattedTime(getStartOfSession())
        ) + RESET);
    }

    private String getFormattedTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm"));
    }

    @SuppressWarnings("java:S106")
    public void displayGoodByeMessage() {
        System.out.println(GREY + String.format(GOOD_BYE_MSG, getDurationOfSession(getStartOfSession())) + RESET);
    }

    public String getDurationOfSession(LocalDateTime startOfSession) {
        var duration = Duration.between(startOfSession, LocalDateTime.now(ZoneId.systemDefault()));
        long totalSecond = duration.getSeconds();
        long hours = totalSecond / SECONDS_PER_HOUR;
        long minutes = (totalSecond % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
        long seconds = totalSecond % SECONDS_PER_MINUTE;
        var sb = new StringBuilder();
        if (hours > 0) sb.append(hours).append("h. ");
        if (minutes > 0) sb.append(minutes).append("m. ");
        if (seconds > 0) sb.append(seconds).append("s.");
        return sb.toString().trim();
    }

    @SuppressWarnings("java:S106")
    public void displayResult(String line) {
        System.out.println(GREEN + line + RESET);
    }

    @SuppressWarnings("java:S106")
    public void displayExceptionMessage(Exception ex) {
        System.err.println(TOMATO + ex.getMessage() + RESET);
    }

    public void handleException(IOException ex) {
        log.error("An exception occurred while reading input - {}", ex.getMessage());
    }
}
