package com.test.calculator.operations;

/**
 * Performs subtraction
 * 
 * @author SAIvanov
 *
 */
public class SubtractOperation implements Operation {

    @Override
    public OperationSubject calculate(OperationSubject firstNumber, OperationSubject secondNumber)
            throws ArithmeticException {
        return firstNumber.subtract(secondNumber);
    }

    @Override
    public String getKey() {
        return "-";
    }

}
