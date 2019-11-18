package com.test.calculator.history;

import java.io.File;
import java.util.List;

import com.test.calculator.operations.Operation;

/**
 * Stores and modifies history of calculations
 * 
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
    void addToHistory(HistoryEntry historyEntry);

    /**
     * Clears history
     */
    void clearHistory();

    /**
     * Sets the runnable that will be executed when history is modified
     * 
     * @param runnable - runnable that will be executed when history is modified
     */
    void setModifyListener(HistoryModifiedListener runnable);

    /**
     * Imports History to file
     * @param file - File to which history will be imported
     */
    void importFromFile(File file);
    
    /**
     * Exports History from file
     * @param file - File from which history will be exported
     */
    void exportToFile(File file);

}
