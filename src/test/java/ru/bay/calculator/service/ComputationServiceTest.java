package ru.bay.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.bay.calculator.config.ApplicationConfigStub;
import ru.bay.calculator.config.OperationConfig;
import ru.bay.calculator.config.OperationConfigStub;
import ru.bay.calculator.config.ValidationConfigStub;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComputationServiceTest {
    private final OperationConfig operationConfig = OperationConfigStub.getInstance();
    private final TranslationService translationService = new TranslationService(ApplicationConfigStub.getInstance());
    private final ValidationService validationService = new ValidationService(
            ApplicationConfigStub.getInstance(),
            ValidationConfigStub.getInstance()
    );
    private final ParserService parserService = new ParserService(validationService, translationService);

    private ComputationService service;

    @BeforeEach
    void setService() {
        service = new ComputationService(operationConfig, translationService, parserService);
    }

    @ParameterizedTest
    @CsvSource({
            "2,1+1",
            "V,X-V",
            "125,5^III",
            "IV,II+II",
            "100,50+L",
            "9,X-1",
            "VI,III*II",
            "III,VI/II",
            "1000,MM-1000",
            "1,XI-10",
            "XL,L-X",
            "XLIX,VII^II",
            "LXIV,VIII^II",
            "LXXXI,IX^II",
            "121,XI^2"

    })
    void shouldReturnTheResultOfComputation(String answer, String input) {
        assertEquals(answer, service.compute(input));
    }
}