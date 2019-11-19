package com.test.calculator.operations;

/**
 * Performs addition
 * 
 * @author SAIvanov
 *
 */
public class AddOperation implements Operation {

    @Override
    public OperationSubject calculate(OperationSubject firstNumber, OperationSubject secondNumber) throws ArithmeticException {
        return firstNumber.add(secondNumber);
    }

    @Override
    public String getKey() {
        return "+";
    }

}
