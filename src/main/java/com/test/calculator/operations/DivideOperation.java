package com.test.calculator.operations;

import java.math.BigDecimal;

/**
 * Performs division
 * 
 * @author SAIvanov
 *
 */
public class DivideOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) throws ArithmeticException {
        return firstNumber.divide(secondNumber);
    }

    @Override
    public String getKey() {
        return "/";
    }

}
