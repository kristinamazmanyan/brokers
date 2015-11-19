package com.brokerexam.common.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils implements Serializable {
	private static final long serialVersionUID = -2890492114668419064L;

	public static final String toString(Date date, String format) {
		if (date == null) {
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	public static final String toString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		return dateFormat.format(date);
	}

	public static Date toDate(String dateString, String dateFormat)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		simpleDateFormat.setLenient(false);
		return simpleDateFormat.parse(dateString);
	}
}
