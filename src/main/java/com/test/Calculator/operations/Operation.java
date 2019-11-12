package com.test.Calculator.operations;

public abstract class Operation {
 abstract double calculate(double firstNumber,double secondNumber) throws Exception; 
 public abstract String getKey();
}
