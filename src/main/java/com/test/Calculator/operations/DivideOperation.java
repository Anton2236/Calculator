package com.test.Calculator.operations;

public class DivideOperation extends Operation {

	@Override
	double calculate(double firstNumber, double secondNumber) {
		return firstNumber / secondNumber;
	}

	@Override
	public String getKey() {
		return "/";
	}

}
