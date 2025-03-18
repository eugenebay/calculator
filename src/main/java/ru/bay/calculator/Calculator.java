package ru.bay.calculator;

public class Calculator {
    public static void main(String[] args) {
        final Application app = ApplicationFactory.FACTORY.getObject(Application.class);
        app.run();
    }
}
