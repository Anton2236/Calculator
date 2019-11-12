package com.test.Calculator;

public class StringUtils {

	/**
	 * Checks if String is null or empty
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}

	
	/**
	 * Adds two strings with line break between them
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String concatWithLineBreaks(String s1, String s2) {
		return isEmpty(s1) ? s2 : (s2 + "\n" + s1);
	}
	

}
