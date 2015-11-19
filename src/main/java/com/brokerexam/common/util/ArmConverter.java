package com.brokerexam.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Properties;

public class ArmConverter {

	static private Properties messages;
	static private Hashtable<Integer, Integer> mapASCIIToUnicode = null;
	static private Hashtable<Integer, Integer> mapUnicodeToASCII = null;

	static {

		if (messages == null) {
			messages = new Properties();
			
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream is = classLoader
					.getResourceAsStream("com/brokerexam/common/util/ArmConverterMap.properties");
			
			if (is == null) {
				System.err
						.println("Cannot find property file for armenian conversion service");
			} else {
				try {
					messages.load(is);
				} catch (Exception e) {
					System.err
							.println("Cannot load properties from input stream");
				}
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}

		String sourceCodes = messages.getProperty("SOURCE_CODES");
		String targetCodes = messages.getProperty("TARGET_CODES");

		mapASCIIToUnicode = new Hashtable<Integer, Integer>();
		mapUnicodeToASCII = new Hashtable<Integer, Integer>();
		int codePointCount = sourceCodes
				.codePointCount(0, sourceCodes.length());
		for (int i = 0; i < codePointCount; i++) {
			mapASCIIToUnicode.put(sourceCodes.codePointAt(i), targetCodes
					.codePointAt(i));
			mapUnicodeToASCII.put(targetCodes.codePointAt(i), sourceCodes
					.codePointAt(i));
		}
	}

	public static String ASCIIToUnicode(String text) {
		String resultStr = null;
		if (text == null) {
			return resultStr;
		}
		try {
			int codePointCount = text.codePointCount(0, text.length());
			StringWriter sw = new StringWriter();

			for (int i = 0; i < codePointCount; i++) {
				Integer unicode = (Integer) mapASCIIToUnicode.get(text
						.codePointAt(i));
				if (unicode != null) {
					sw.write(unicode);
				} else {
					sw.write(text.codePointAt(i));
				}
			}

			resultStr = new String(sw.getBuffer());

		} catch (Exception e) {
			System.err.println(e
					+ (e.getMessage() != null ? e.getMessage() : ""));
		}

		return resultStr;
	}

	public static String UnicodeToASCII(String text) {
		String resultStr = null;
		if (text == null) {
			return resultStr;
		}
		try {
			int codePointCount = text.codePointCount(0, text.length());
			StringWriter sw = new StringWriter();

			for (int i = 0; i < codePointCount; i++) {
				Integer ascii = (Integer) mapUnicodeToASCII.get(text
						.codePointAt(i));
				if (ascii != null) {
					sw.write(ascii);
				} else {
					sw.write(text.codePointAt(i));
				}
			}

			resultStr = new String(sw.getBuffer());

		} catch (Exception e) {
			System.err.println(e
					+ (e.getMessage() != null ? e.getMessage() : ""));
		}

		return resultStr;
	}
}
