package ru.bay.calculator.operation;

public class MultiplyOperation implements Operation {
    @Override
    public int process(int firstNum, int secondNum) {
        return Math.multiplyExact(firstNum, secondNum);
    }
}
