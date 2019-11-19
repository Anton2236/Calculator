package com.test.calculator.operations;

/**
 * Performs multiplication
 * 
 * @author SAIvanov
 *
 */
public class MultiplyOperation implements Operation {

    @Override
    public OperationSubject calculate(OperationSubject firstNumber, OperationSubject secondNumber)
            throws ArithmeticException {
        return firstNumber.multiply(secondNumber);
    }

    @Override
    public String getKey() {
        return "*";
    }

}
