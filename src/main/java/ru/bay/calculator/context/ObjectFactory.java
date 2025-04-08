package ru.bay.calculator.context;

import lombok.SneakyThrows;
import ru.bay.calculator.exception.ObjectCreationException;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.invoke.MethodType.methodType;
import static ru.bay.calculator.service.utility.CalculatorUtil.isSingleton;
import static ru.bay.calculator.service.utility.CalculatorUtil.simplifyName;

// Suppress SonarQube enum singleton warning.
@SuppressWarnings("java:S6548")
public enum ObjectFactory implements ObjectFinder {
    GET;

    private final MethodHandles.Lookup lookup = MethodHandles.lookup();

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T createObject(Class<T> clazz) {
        try {
            T instance = (T) lookup.findConstructor(clazz, methodType(void.class))
                    .invoke();
            if (isSingleton(clazz)) {
                registerAnObject(instance);
            }
            return instance;
        } catch (NoSuchMethodException ex) {
            var con = findConstructor(clazz);
            var types = con.getParameterTypes();
            var param = getParamInstance(types);
            T instance = (T) lookup.findConstructor(clazz, methodType(void.class, types))
                    .invokeWithArguments(param);
            if (isSingleton(clazz)) {
                registerAnObject(instance);
            }
            return instance;
        }
    }

    private <T> void registerAnObject(T instance) {
        var className = simplifyName(instance.getClass());
        ObjectStorage.STORAGE.put(className, instance);
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors())
                .findFirst()
                .orElseThrow(() -> new ObjectCreationException("Parameterized constructor missing."));
    }

    private Object[] getParamInstance(Class<?>[] types) {
        var params = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            var type = types[i];
            if (ObjectStorage.STORAGE.contains(type.getSimpleName())) {
                params[i] = ObjectStorage.STORAGE.get(type.getSimpleName());
            } else {
                params[i] = createObject(type);
            }
        }
        return params;
    }

    @Override
    public List<Class<?>> findByCondition(String location, Predicate<Class<?>> predicate) {
        return ObjectFinder.super.findByCondition(location, predicate);
    }
}
