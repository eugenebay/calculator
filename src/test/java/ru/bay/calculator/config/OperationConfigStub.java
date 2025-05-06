package ru.bay.calculator.config;

import ru.bay.calculator.operation.*;

import java.util.Map;

public class OperationConfigStub implements OperationConfig {
    @Override
    public Map<String, Operation> getOperations() {
        return Map.of(
                "+", new AddOperation(),
                "-", new SubOperation(),
                "*", new MultiplyOperation(),
                "/", new DivOperation(),
                "^", new PowOperation()
        );
    }
}
