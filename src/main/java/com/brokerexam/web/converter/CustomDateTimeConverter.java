package com.brokerexam.web.converter;

import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

import com.brokerexam.common.util.Constants;

public class CustomDateTimeConverter extends DateTimeConverter {
	public CustomDateTimeConverter() {
		super();
		setTimeZone(TimeZone.getDefault());
		setPattern(Constants.DATE_TIME_FORMAT);
	}
}
