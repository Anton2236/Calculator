package com.test.calculator.operations;

/**
 * Performs division
 * 
 * @author SAIvanov
 *
 */
public class DivideOperation implements Operation {

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }

    @Override
    public String getKey() {
        return "/";
    }

}
