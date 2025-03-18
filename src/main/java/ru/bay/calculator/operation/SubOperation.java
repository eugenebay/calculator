package ru.bay.calculator.operation;

public class SubOperation implements Operation {
    @Override
    public int process(int firstNum, int secondNum) {
        return Math.subtractExact(firstNum, secondNum);
    }
}
