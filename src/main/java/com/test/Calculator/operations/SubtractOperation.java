package com.test.Calculator.operations;

public class SubtractOperation extends Operation {

	@Override
	double calculate(double firstNumber, double secondNumber) throws Exception {
		return firstNumber-secondNumber;
	}

	@Override
	public String getKey() {
		return "-";
	}

}
