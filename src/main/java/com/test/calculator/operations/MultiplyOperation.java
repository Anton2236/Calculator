package com.test.calculator.operations;

import java.math.BigDecimal;

/**
 * Performs multiplication
 * 
 * @author SAIvanov
 *
 */
public class MultiplyOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) throws ArithmeticException {
        return firstNumber.multiply(secondNumber);
    }

    @Override
    public String getKey() {
        return "*";
    }

}
