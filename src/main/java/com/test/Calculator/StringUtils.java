package com.test.Calculator;

import java.text.DecimalFormat;

import javax.swing.text.NumberFormatter;

public class StringUtils {

	private static NumberFormatter numberFormatter = new NumberFormatter(new DecimalFormat());

	/**
	 * Checks if String is null or empty
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}
	/**
	 * Returns double formatted with DecimalFormat
	 * @param d
	 * @return
	 */
	public static String formatDouble(double d) {
		try {
			return numberFormatter.valueToString(d);
		} catch (Exception e) {
			return "Error";
		}
	}

}
