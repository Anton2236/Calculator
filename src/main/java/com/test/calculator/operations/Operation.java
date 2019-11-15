package com.test.calculator.operations;

public interface Operation {

    /**
     * Performs calculation of two numbers
     * 
     * @param firstNumber
     * @param secondNumber
     * @return
     * @throws Exception
     */
    double calculate(double firstNumber, double secondNumber);

    /**
     * Returns String representation of operation
     * 
     * @return
     */
    String getKey();
}
