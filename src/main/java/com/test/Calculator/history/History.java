package com.test.Calculator.history;

import java.util.List;

import com.test.Calculator.operations.Operation;


public abstract class History {
 public abstract List<String> getHistory();
 public abstract void addToHistory(double  firstNumber, double secondNumber,double result, Operation operation);
 public abstract void clearHistory();
 
 public abstract void setModifyListener(Runnable runnable);
 
}
