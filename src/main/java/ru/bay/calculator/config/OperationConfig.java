package ru.bay.calculator.config;

import ru.bay.calculator.operation.Operation;

import java.util.Map;

public interface OperationConfig {
    Map<String, Operation> getOperations();
}
