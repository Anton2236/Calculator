package com.test.calculator.history;

import java.util.List;

import com.test.calculator.operations.Operation;

/**
 * Stores and modifies history of calculations
 * @author SAIvanov
 *
 */
public interface History {
    /**
     * returns History List
     * 
     * @return
     */
    List<HistoryEntry> getHistory();

    /**
     * Adds operation to history
     * 
     * @param firstNumber - first number
     * @param secondNumber - second number
     * @param result - result of calculation
     * @param operation - instance of operation that was performed during calculation
     */
    void addToHistory(double firstNumber, double secondNumber, double result, Operation operation);

    /**
     * Clears history
     */
    void clearHistory();

    /**
     * Sets the runnable that will be executed when history is modified
     * 
     * @param runnable - runnable that will be executed when history is modified
     */
    void setModifyListener(Runnable runnable);

}
