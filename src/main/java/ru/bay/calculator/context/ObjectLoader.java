package ru.bay.calculator.context;

import lombok.extern.slf4j.Slf4j;
import ru.bay.calculator.service.utility.CalculatorUtil;

import java.util.List;
import java.util.Objects;

@Slf4j
class ObjectLoader implements ObjectFinder {
    private static final String PROJECT_PATH = "src/main/java/ru/bay/calculator";

    void load() {
        var classes = findByCondition(
                PROJECT_PATH,
                draft -> CalculatorUtil.isNotInterface(draft) && draft.isAnnotationPresent(Component.class)
        );
        if (Objects.nonNull(classes)) {
            ObjectStorage.DRAFTS.addAll(classes);
            initializationOfObjects(ObjectStorage.DRAFTS.get());
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void initializationOfObjects(List<Class<?>> classes) {
        classes.forEach(clazz -> {
            T instance = (T) ObjectFactory.GET.createObject(clazz);
            if (Objects.nonNull(instance)) {
                log.info("The {} class has been created.", instance.getClass().getSimpleName());
            }
        });
    }
}
