package com.test.calculator.history;

import java.util.ArrayList;
import java.util.List;

import com.test.calculator.operations.Operation;

/**
 * History of calculations, which stores entries in ArrayList
 * 
 * @author SAIvanov
 *
 */
public class SessionHistory implements History {

    private Runnable modifyListener;

    private List<HistoryEntry> history;

    public SessionHistory() {
        modifyListener = null;
        history = new ArrayList<HistoryEntry>();
    }

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
