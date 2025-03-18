package ru.bay.calculator.operation;

public class DivOperation implements Operation {
    @Override
    public int process(int firstNum, int secondNum) {
        return Math.divideExact(firstNum, secondNum);
    }
}
