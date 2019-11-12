package com.test.Calculator.operations;

public abstract class Operation {

	/**
	 * Performs calculation of two numbers
	 * 
	 * @param firstNumber
	 * @param secondNumber
	 * @return
	 * @throws Exception
	 */
	abstract double calculate(double firstNumber, double secondNumber) throws Exception;

	/**
	 * Returns String representation of operation
	 * 
	 * @return
	 */
	public abstract String getKey();
}
