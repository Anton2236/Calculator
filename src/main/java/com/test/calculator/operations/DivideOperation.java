package com.test.calculator.operations;

/**
 * Performs division
 * 
 * @author SAIvanov
 *
 */
public class DivideOperation implements Operation {

    @Override
    public OperationSubject calculate(OperationSubject firstNumber, OperationSubject secondNumber)
            throws ArithmeticException {
        return firstNumber.divide(secondNumber);
    }

    @Override
    public String getKey() {
        return "/";
    }

}
