package ru.bay.calculator.context;

import static ru.bay.calculator.service.utility.CalculatorUtil.simplifyName;

public class ApplicationContext {
    public ApplicationContext() {
        ObjectLoader loader = ObjectFactory.GET.createObject(ObjectLoader.class);
        loader.load();
    }

    public <T> T getObject(Class<T> clazz) {
        var className = simplifyName(clazz);
        if (ObjectStorage.STORAGE.contains(className)) {
            return clazz.cast(ObjectStorage.STORAGE.get(className));
        }
        return ObjectFactory.GET.createObject(clazz);
    }
}
