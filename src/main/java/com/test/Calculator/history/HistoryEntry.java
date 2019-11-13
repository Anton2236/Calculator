package com.test.Calculator.history;

import com.test.Calculator.StringUtils;
import com.test.Calculator.operations.Operation;

public class HistoryEntry {

	private double firstNumber;
	private double secondNumber;
	private double result;
	private Operation operation;

	public HistoryEntry(double firstNumber, double secondNumber, double result,
			Operation operation) {
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
		return String.format("%s %s %s = %s", firstNumberString, operation.getKey(), secondNumberString,
				resultString);
	}
	
	
	
	

}
