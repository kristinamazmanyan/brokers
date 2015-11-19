package com.brokerexam.common.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

public class ConverterUtils {
	static Hashtable<String, Properties> localizationMap = new Hashtable<String, Properties>();
	private static String className;

	static public void init(String classSimpleName) {
		className = classSimpleName;
		Properties messages = new Properties();
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();

		InputStream is = classLoader
				.getResourceAsStream("com/exam/common/util/" + className + ".properties");

		try {
			messages.load(is);
			localizationMap.put(className, messages);
		} catch (Exception e) {
			System.out.println("Cannot find property file for localization class: "
							+ className);
		}
		try {
			is.close();
		} catch (IOException e) {
		}
	}

	static public String getWord(String key) {
		return localizationMap.get(className).getProperty(key);
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

}
