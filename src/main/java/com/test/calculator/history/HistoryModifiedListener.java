package com.test.calculator.history;

/**
 * Listener that notifies about history modifications
 * 
 * @author SAIvanov
 *
 */
@FunctionalInterface
public interface HistoryModifiedListener {
    void onModified(History history);
}
