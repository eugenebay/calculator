package ru.bay.calculator.operation;

public class PowOperation implements Operation {
    @Override
    public int process(int number, int power) {
        if (power < 1) throw new ArithmeticException("the power must be greater than zero");
        double result = Math.pow(number, power);
        if (Integer.MAX_VALUE < result) throw new ArithmeticException("integer overflow");
        return (int) result;
    }
}
