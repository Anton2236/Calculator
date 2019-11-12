package com.test.Calculator.history;

import java.util.ArrayList;
import java.util.List;

import com.test.Calculator.StringUtils;
import com.test.Calculator.operations.Operation;

public class SessionHistory extends History {

	private Runnable modifyListener;

	public SessionHistory() {
		modifyListener = null;
		history = new ArrayList<String>();
	}

	private List<String> history;

	@Override
	public List<String> getHistory() {
		return new ArrayList<String>(history);
	}

	@Override
	public void addToHistory(double firstNumber, double secondNumber, double result, Operation operation) {
		String firstNumberString = String.valueOf(firstNumber);
		String secondNumberString = String.valueOf(secondNumber);
		String resultString = String.valueOf(result);
		history.add(String.format("%s %s %s = %s", firstNumberString, operation.getKey(), secondNumberString,
				resultString));
		onModify();
	}

	@Override
	public void clearHistory() {
		history = new ArrayList<String>();
		onModify();

	}

	private void onModify() {
		if (modifyListener != null) {
			modifyListener.run();
		}
	}

	@Override
	public void setModifyListener(Runnable modifyListener) {
		this.modifyListener = modifyListener;
	}

}
