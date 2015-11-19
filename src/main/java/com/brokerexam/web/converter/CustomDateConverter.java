package com.brokerexam.web.converter;

import java.util.TimeZone;

import javax.faces.convert.DateTimeConverter;

import com.brokerexam.common.util.Constants;

public class CustomDateConverter extends DateTimeConverter {
	public CustomDateConverter() {
		super();
		setTimeZone(TimeZone.getDefault());
		setPattern(Constants.DATE_FORMAT);
	}
}
