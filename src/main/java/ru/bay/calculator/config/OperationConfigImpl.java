package ru.bay.calculator.config;

import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.context.ObjectFactory;
import ru.bay.calculator.operation.Operation;
import ru.bay.calculator.utility.CalculatorUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OperationConfigImpl implements OperationConfig {
    private static final String OPERATION_PATH = "src/main/java/ru/bay/calculator/operation";

    private final ApplicationConfig applicationConfig;
    private final Map<String, Operation> operations;

    public OperationConfigImpl(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
        this.operations = populateOperations();
    }

    @Override
    public Map<String, Operation> getOperations() {
        return Map.copyOf(operations);
    }

    private Map<String, Operation> populateOperations() {
        var classes = ObjectFactory.GET.findByCondition(
                OPERATION_PATH,
                clazz -> CalculatorUtil.isNotInterfaceAndNotDisabled(clazz)
                        && Operation.class.isAssignableFrom(clazz)
        );
        if (classes.isEmpty()) {
            throw new IllegalStateException("No operation classes found in path - " + OPERATION_PATH);
        }
        return applicationConfig.getOperators()
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> createOperation(entry.getValue(), classes)));
    }

    private Operation createOperation(String operationPrefix, List<Class<?>> classes) {
        return classes.stream()
                .filter(clazz -> clazz.getSimpleName().startsWith(operationPrefix))
                .findFirst()
                .map(clazz -> (Operation) ObjectFactory.GET.createObject(clazz))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Operation %s not found", operationPrefix)));
    }
}
