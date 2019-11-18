package com.test.calculator.operations;

import com.test.calculator.history.History;
import com.test.calculator.history.HistoryEntry;

/**
 * Stores all available operations and performs calculations with them
 * 
 * @author SAIvanov
 *
 */
public class OperationsManager {

    private History history;

    private Operation[] operations;

    /**
     * Creates Operations Manager instance
     * 
     * @param history - history instance
     */
    public OperationsManager(History history) {
        this.history = history;
        operations = new Operation[4];
        operations[0] = new AddOperation();
        operations[1] = new SubtractOperation();
        operations[2] = new MultiplyOperation();
        operations[3] = new DivideOperation();
    }

    /**
     * Returns String array of operations
     * 
     * @return String array of operations keys
     */

    public String[] getOperationKeysArray() {
        String[] operationKeys = new String[operations.length];

        for (int i = 0; i < operations.length; i++) {
            operationKeys[i] = operations[i].getKey();
        }
        return operationKeys;
    }

    /**
     * Returns operation by index
     * 
     * @param index - index of operation
     * @return operation instance
     */
    public Operation getOperation(int index) {
        return operations[index];
    }

    /**
     * Returns String result of calculation
     * 
     * @param firstNumberString
     * @param secondNumberString
     * @param operationIndex
     * @return
     */
    public Double calculateResult(double firstNumber, double secondNumber, Operation operation) {

        Double result = operation.calculate(firstNumber, secondNumber);
        HistoryEntry historyEntry = new HistoryEntry(firstNumber, secondNumber, result, operation.getKey());
        history.addToHistory(historyEntry);

        return result;
    }

}
