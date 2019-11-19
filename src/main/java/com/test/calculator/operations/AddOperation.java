package com.test.calculator.operations;

import java.math.BigDecimal;

/**
 * Performs addition
 * 
 * @author SAIvanov
 *
 */
public class AddOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber) throws ArithmeticException {
        return firstNumber.add(secondNumber);
    }

    @Override
    public String getKey() {
        return "+";
    }

}
