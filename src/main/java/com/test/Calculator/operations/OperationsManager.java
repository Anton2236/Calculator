package com.test.Calculator.operations;

import com.test.Calculator.history.History;

public class OperationsManager {

	private History history;

	private Operation[] operations;

	/**
	 * Creates Operations Manager instance
	 * 
	 * @param history
	 */
	public OperationsManager(History history) {
		this.history = history;
		operations = new Operation[4];
		operations[0] = new PlusOperation();
		operations[1] = new SubtractOperation();
		operations[2] = new MultiplyOperation();
		operations[3] = new DivideOperation();
	}

	/**
	 * Returns String array of operations
	 * 
	 * @return
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
