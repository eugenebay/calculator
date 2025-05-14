package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfigStub;
import ru.bay.calculator.config.ValidationConfigStub;

import static org.junit.jupiter.api.Assertions.*;

class ParserServiceTest {
    private final ValidationService validationService = new ValidationService(
            ApplicationConfigStub.getInstance(),
            ValidationConfigStub.getInstance()
    );
    private final TranslationService translationService = new TranslationService(ApplicationConfigStub.getInstance());

    private ParserService service;

    @BeforeEach
    void setService() {
        service = new ParserService(validationService, translationService);
    }

    @ParameterizedTest
    @CsvSource({
            "1,+,1,1+1",
            "10,-,5,10-5",
            "5,^,5,5^5",
            "10,+,5,X+V",
            "10,^,5,X^V",
            "4,*,3,IV*III",
            "100,/,10,100/10",
            "-1,-,2,-1-2",
            "20,+,10,20+X",
            "9,-,4,IX-IV",
            "3,^,3,III^3",
            "0,+,0,0+0",
            "7,/,3,7/III",
            "3000,+,1,MMM+I"
    })
    void shouldReturnTheCorrectParsingResultIfInputStringIsValid(
            int firstNum,
            String operator,
            int secondNum,
            String input
    ) {
        var parserResult = service.parse(input);
        assertAll("Asserting ParserResult",
                () -> assertEquals(firstNum, parserResult.firstNumber()),
                () -> assertEquals(operator, parserResult.operator()),
                () -> assertEquals(secondNum, parserResult.secondNumber())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "1+",
            "+1",
            "++2",
            "1++2",
            "1  +  2",
            "X *",
            "10X",
            "abc",
            "IVV+X",
            "5+Z",
            "X+",
            "IIII-VV",
            "1000+LL",
            "DD*IC",
            "MMMM/1000",
            "IIV+123",
            "XXC*90",
            "VVX+CCD",
            "9000+ABC"
    })
    void shouldReturnIllegalArgumentExceptionWhenInputStringIsNotValid(String input) {
        assertThrows(IllegalArgumentException.class, () -> service.parse(input));
    }
}