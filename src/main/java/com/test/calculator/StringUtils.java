package com.test.calculator;

/**
 * Some string utilities
 * 
 * @author SAIvanov
 *
 */
public class StringUtils {

    /**
     * Checks if String is null, empty or minus
     * 
     * @param string - string to be checked
     * @return true - if string null, empty or equals "-"
     */
    public static boolean isEmptyOrMinus(String string) {
        return string == null || string.isEmpty() || string.equals("-");
    }

}
