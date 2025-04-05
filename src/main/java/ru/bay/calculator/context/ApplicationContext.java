package ru.bay.calculator.context;

import static ru.bay.calculator.service.utility.CalculatorUtil.simplifyName;

public class ApplicationContext {
    public ApplicationContext() {
        ObjectLoader loader = ObjectFactory.GET.createObject(ObjectLoader.class);
        loader.load();
    }

    public <T> T getObject(Class<T> clazz) {
        var store = ObjectStorage.STORAGE.getStore();
        var className = simplifyName(clazz);
        if (store.containsKey(className)) {
            return clazz.cast(store.get(className));
        }
        return ObjectFactory.GET.createObject(clazz);
    }
}
