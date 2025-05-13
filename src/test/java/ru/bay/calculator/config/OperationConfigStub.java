package ru.bay.calculator.config;

import ru.bay.calculator.operation.*;

import java.util.Map;

public class OperationConfigStub implements OperationConfig {
    private static boolean isInstanceCreated = false;

    private OperationConfigStub() {
        // Protection from creation via reflection
        if (isInstanceCreated) throw new IllegalStateException("OperationConfigStub already initialized.");
        isInstanceCreated = true;
    }

    public static OperationConfigStub getInstance() {
        return OperationConfigStub.TestOperationConfigurationHolder.INSTANCE;
    }

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

    private static class TestOperationConfigurationHolder {
        private static final OperationConfigStub INSTANCE = new OperationConfigStub();
    }
}
