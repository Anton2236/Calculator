package com.test.calculator.operations;

/**
 * Performs subtraction
 * 
 * @author SAIvanov
 *
 */
public class SubtractOperation implements Operation {

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }

    @Override
    public String getKey() {
        return "-";
    }

}
