package com.test.Calculator.history;

import java.util.ArrayList;
import java.util.List;

import com.test.Calculator.operations.Operation;

public class SessionHistory extends History {

	public SessionHistory() 
	{
		history = new ArrayList<String>();
	}
	
	private List<String> history;
	
	
	@Override
	public List<String> getHistory() {
		return new ArrayList<String>(history);
	}

	@Override
	public void addToHistory(double firstNumber, double secondNumber, double result, Operation operation) {
		history.add(String.format("%f %s %f = %f ", firstNumber,operation.getKey(),secondNumber,result));
	}

	@Override
	public void clearHistory() {
		history = new ArrayList<String>();

	}

}
