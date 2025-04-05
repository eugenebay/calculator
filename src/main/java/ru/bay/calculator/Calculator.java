package ru.bay.calculator;

import ru.bay.calculator.context.ApplicationContext;
import ru.bay.calculator.context.ObjectFactory;
import ru.bay.calculator.service.Application;

public class Calculator {
    public static void main(String[] args) {
        final ApplicationContext context = ObjectFactory.GET.createObject(ApplicationContext.class);
        final Application app = context.getObject(Application.class);
        app.run();
    }
}
