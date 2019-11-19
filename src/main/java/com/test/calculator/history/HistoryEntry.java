package com.test.calculator.history;

import com.test.calculator.operations.OperationSubject;

/**
 * Stores data about performed calculation
 * 
 * @author SAIvanov
 *
 */
public class HistoryEntry {

    private OperationSubject firstNumber;
    private OperationSubject secondNumber;
    private OperationSubject result;
    private String operation;

    /**
     * Creates an instance of History entry
     * 
     * @param firstNumber - first number
     * @param secondNumber - second number
     * @param result - result of calculation
     * @param operation - instance of operation that was performed during calculation
     */
    public HistoryEntry(OperationSubject firstNumber, OperationSubject secondNumber, OperationSubject result,
            String operation) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.result = result;
        this.operation = operation;
    }

    @Override
    public String toString() {
        String firstNumberString = firstNumber.toString();
        String secondNumberString = secondNumber.toString();
        String resultString = result.toString();
        return String.format("%s %s %s = %s", firstNumberString, operation, secondNumberString, resultString);
    }

}
