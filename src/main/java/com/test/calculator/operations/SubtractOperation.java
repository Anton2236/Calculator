package com.test.calculator.operations;

import java.math.BigDecimal;

/**
 * Performs subtraction
 * 
 * @author SAIvanov
 *
 */
public class SubtractOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) throws ArithmeticException {
        return firstNumber.subtract(secondNumber);
    }

    @Override
    public String getKey() {
        return "-";
    }

}
