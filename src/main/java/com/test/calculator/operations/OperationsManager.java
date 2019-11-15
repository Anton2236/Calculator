package com.test.calculator.operations;

import com.test.calculator.history.History;
/**
 * Stores all available operations and performs calculations with them
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
     * Returns String result of calculation
     * 
     * @param firstNumberString
     * @param secondNumberString
     * @param operationIndex
     * @return
     */
    public Double getResult(double firstNumber, double secondNumber, int operationIndex) {

        Operation operation = operations[operationIndex];

        Double result = operation.calculate(firstNumber, secondNumber);

        try {
            history.addToHistory(firstNumber, secondNumber, result, operation);
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

}
