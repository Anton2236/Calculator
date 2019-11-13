package com.test.Calculator.history;

import java.util.ArrayList;
import java.util.List;

import com.test.Calculator.StringUtils;
import com.test.Calculator.operations.Operation;

public class SessionHistory extends History {

	private Runnable modifyListener;

	public SessionHistory() {
		modifyListener = null;
		history = new ArrayList<HistoryEntry>();
	}

	private List<HistoryEntry> history;

	@Override
	public List<HistoryEntry> getHistory() {
		return new ArrayList<HistoryEntry>(history);
	}

	@Override
	public void addToHistory(double firstNumber, double secondNumber, double result, Operation operation) {
		history.add(0, new HistoryEntry(firstNumber, secondNumber, result, operation));
		onModify();
	}

	@Override
	public void clearHistory() {
		history = new ArrayList<HistoryEntry>();
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
