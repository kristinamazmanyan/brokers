package com.brokerexam.common.util;

public class TextUtils {
	
	
	public static boolean hasValueAndIsNotZero(Object value) {
		if (value == null) {
			return false;
		}
		
		if (value instanceof String) {
			String strValue = (String) value;
			return (strValue.trim().length() > 0);
		}
		
		if (value instanceof Number) {
			String number = (String) value.toString();
			return !(number.equals(Constants.ZERO) || number.equals(Constants.ZERO_));
		}
		return true;
	}


	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean hasText(Object ob) {
		if (!(ob instanceof String)) {
			return false;
		}
		String value = (String) ob;
		return (value.trim().length() > 0);
	}

	public static boolean isEmpty(Object ob) {
		return !hasText(ob);
	}

	public static String ltrim(String source) {
		return source.replaceAll("^\\s+", "");
	}

	public static String rtrim(String source) {
		return source.replaceAll("\\s+$", "");
	}

	public static String itrim(String source) {
		return source.replaceAll("\\b\\s{2,}\\b", " ");
	}

	public static String trim(String str) {
		return hasText(str) ? ltrim(rtrim(str)) : "";
	}

	public static String lrtrim(String source) {
		return ltrim(rtrim(source));
	}

	
}
