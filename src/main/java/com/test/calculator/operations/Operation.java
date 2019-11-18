package com.test.calculator.operations;

import java.io.Serializable;

public interface Operation extends Serializable {

    /**
     * Performs calculation of two numbers
     * 
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    double calculate(double firstNumber, double secondNumber);

    /**
     * Returns String representation of operation
     * 
     * @return
     */
    String getKey();
}
