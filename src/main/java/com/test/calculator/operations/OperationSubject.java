package com.test.calculator.operations;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Wraps value for operations performing
 * 
 * @author SAIvanov
 *
 */
public class OperationSubject {

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_EVEN);
    private BigDecimal value;

    /**
     * Creates instance of Operation subject
     * 
     * @param string - string representation of value
     * @throws NumberFormatException
     */
    public OperationSubject(String string) throws NumberFormatException {
        value = new BigDecimal(string);
    }

    private OperationSubject(BigDecimal value) {
        this.value = value;
    }

    /**
     * Performs add operation
     * 
     * @param otherSubject - other subject
     * @return result of addition
     * @throws ArithmeticException
     */
    public OperationSubject add(OperationSubject otherSubject) throws ArithmeticException {
        BigDecimal result = this.value.add(otherSubject.value, MATH_CONTEXT);
        return new OperationSubject(result);
    }

    /**
     * Performs subtraction operation
     * 
     * @param otherSubject - other subject
     * @return result of subtraction
     * @throws ArithmeticException
     */
    public OperationSubject subtract(OperationSubject otherSubject) throws ArithmeticException {
        BigDecimal result = this.value.subtract(otherSubject.value, MATH_CONTEXT);
        return new OperationSubject(result);
    }

    /**
     * Performs multiplication operation
     * 
     * @param otherSubject - other subject
     * @return result of multiplication
     * @throws ArithmeticException
     */
    public OperationSubject multiply(OperationSubject otherSubject) throws ArithmeticException {
        BigDecimal result = this.value.multiply(otherSubject.value, MATH_CONTEXT);
        return new OperationSubject(result);
    }

    /**
     * Performs division operation
     * 
     * @param otherSubject - other subject
     * @return result of division
     * @throws ArithmeticException
     */
    public OperationSubject divide(OperationSubject otherSubject) throws ArithmeticException {
        BigDecimal result = this.value.divide(otherSubject.value, MATH_CONTEXT);
        return new OperationSubject(result);
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
