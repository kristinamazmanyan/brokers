package com.brokerexam.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

public class Utils {

	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	public static double round(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public static double formatDouble(double value) {
		String str = Constants.DECIMAL_FORMAT.format(value);

		try {
			return Double.valueOf(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static void closeOutputStream(OutputStream outStream) {
		if (outStream != null) {
			try {
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static void closeInputStream(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
}
