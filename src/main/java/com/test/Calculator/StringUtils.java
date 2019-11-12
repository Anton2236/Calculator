package com.test.Calculator;

public class StringUtils {

	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}

	public static String concatWithLineBreaks(String s1, String s2) {
		return isEmpty(s1) ? s2 : (s2 + "\n" + s1);
	}
	
	public static String convertDouble(double d) {
		if (d % 1.0 != 0)
		    return String.format("%s", d);
		else
		    return String.format("%.0f",d);
	}


}
