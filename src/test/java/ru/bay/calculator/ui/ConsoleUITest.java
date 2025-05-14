package ru.bay.calculator.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfigStub;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleUITest {
    private ConsoleUI consoleUI;

    @BeforeEach
    void setUp() {
        consoleUI = new ConsoleUI(ApplicationConfigStub.getInstance());
    }

    @ParameterizedTest
    @CsvSource({
            "12s.,0,0,12",
            "5h. 45m. 6s.,5,45,6",
            "2h.,2,0,0",
            "1h. 3s.,1,0,3",
            "15m. 10s.,0,15,10",
            "3h. 25m.,3,25,0",
            "7s.,0,0,7",
            "45m.,0,45,0",
            "1h. 1m. 1s.,1,1,1",
            "1s.,0,0,1"
    })
    void shouldReturnAStringConvertingSecondsToCurrentTimeUnits(String expected, int hour, int minute, int second) {
        var now = LocalDateTime.now();
        var startOfSession = now.minusHours(hour).minusMinutes(minute).minusSeconds(second);
        assertEquals(expected, consoleUI.getDurationOfSession(startOfSession));
    }
}
