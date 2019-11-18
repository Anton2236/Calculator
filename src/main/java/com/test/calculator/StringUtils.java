package com.test.calculator;

import java.text.DecimalFormat;

import javax.swing.text.NumberFormatter;

/**
 * Some string utilities
 * 
 * @author SAIvanov
 *
 */
public class StringUtils {

    private static NumberFormatter numberFormatter = new NumberFormatter(new DecimalFormat("0.##########"));

    /**
     * Checks if String is null, empty or minus
     * 
     * @param string - string to be checked
     * @return true - if string null, empty or equals "-"
     */
    public static boolean isEmptyOrMinus(String string) {
        return string == null || string.isEmpty() || string.equals("-");
    }

    /**
     * Returns double formatted with DecimalFormat
     * 
     * @param doubleToFormat - double to format
     * @return formatted double
     */
    public static String formatDouble(double doubleToFormat) {
        try {
            return numberFormatter.valueToString(doubleToFormat);
        } catch (Exception e) {
            return "Error";
        }
    }

}
