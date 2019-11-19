package com.test.calculator.operations;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Operation extends Serializable {

    /**
     * Performs calculation of two numbers
     * 
     * @param firstNumber
     * @param secondNumber
     * @return
     * @throws ArithmeticException
     */
    BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) throws ArithmeticException;

    /**
     * Returns String representation of operation
     * 
     * @return
     */
    String getKey();
}
