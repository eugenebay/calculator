package ru.bay.calculator.operation;

public class AddOperation implements Operation {
    @Override
    public int process(int firstNum, int secondNum) {
        return Math.addExact(firstNum, secondNum);
    }
}
