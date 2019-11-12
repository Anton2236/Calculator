package com.test.Calculator.history;

import java.util.List;

import com.test.Calculator.operations.Operation;


public abstract class History {
	/**
	 * returns History List
	 * @return List<String>
	 */
 public abstract List<String> getHistory();
 
 /**
  * Adds operation to history
  * @param firstNumber
  * @param secondNumber
  * @param result
  * @param operation
  */
 public abstract void addToHistory(double  firstNumber, double secondNumber,double result, Operation operation);
 
 /**
  * Clears history
  */
 public abstract void clearHistory();
 
 /**
  * Sets the listener who will be notified when history is modified
  * @param runnable 
  */
 public abstract void setModifyListener(Runnable runnable);
 
}
