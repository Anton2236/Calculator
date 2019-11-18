package com.test.calculator.operations;

/**
 * Performs multiplication
 * 
 * @author SAIvanov
 *
 */
public class MultiplyOperation implements Operation {

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }

    @Override
    public String getKey() {
        return "*";
    }

}
