package com.test.calculator.operations;

/**
 * Performs addition
 * 
 * @author SAIvanov
 *
 */
public class AddOperation implements Operation {

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    @Override
    public String getKey() {
        return "+";
    }

}
