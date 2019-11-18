package com.test.calculator.history;

import com.test.calculator.StringUtils;
import com.test.calculator.operations.Operation;

/**
 * Stores data about performed calculation
 * 
 * @author SAIvanov
 *
 */
public class HistoryEntry {

    private double firstNumber;
    private double secondNumber;
    private double result;
    private String operation;

    /**
     * Creates an instance of History entry
     * 
     * @param firstNumber - first number
     * @param secondNumber - second number
     * @param result - result of calculation
     * @param operation - instance of operation that was performed during calculation
     */
    public HistoryEntry(double firstNumber, double secondNumber, double result, String operation) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.result = result;
        this.operation = operation;
    }

    @Override
    public String toString() {
        String firstNumberString = StringUtils.formatDouble(firstNumber);
        String secondNumberString = StringUtils.formatDouble(secondNumber);
        String resultString = StringUtils.formatDouble(result);
        return String.format("%s %s %s = %s", firstNumberString, operation, secondNumberString, resultString);
    }

}
