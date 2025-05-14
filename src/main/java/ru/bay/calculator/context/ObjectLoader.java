package ru.bay.calculator.context;

import lombok.extern.slf4j.Slf4j;
import ru.bay.calculator.annotation.Component;
import ru.bay.calculator.utility.CalculatorUtil;

import java.util.List;
import java.util.Objects;

@Slf4j
class ObjectLoader implements ObjectFinder {
    private static final String PROJECT_PATH = "src/main/java/ru/bay/calculator";

    void load() {
        var classes = findByCondition(
                PROJECT_PATH,
                clazz -> CalculatorUtil.isNotInterface(clazz) && clazz.isAnnotationPresent(Component.class)
        );
        if (Objects.nonNull(classes)) {
            initializationOfObjects(classes);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void initializationOfObjects(List<Class<?>> classes) {
        classes.forEach(clazz -> {
            T instance = (T) ObjectFactory.GET.createObject(clazz);
            log.debug("The {} class has been created.", instance.getClass().getSimpleName());
        });
    }
}
