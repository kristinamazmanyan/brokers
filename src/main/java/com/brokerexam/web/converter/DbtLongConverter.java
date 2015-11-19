package com.brokerexam.web.converter;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.LongConverter;

import com.brokerexam.common.util.TextUtils;
import com.brokerexam.web.util.MessageUtil;

public class DbtLongConverter extends LongConverter implements Serializable {

	private static final long serialVersionUID = 8859113707239959972L;
	
	private static final Long ZERO = Long.valueOf(0);

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (!TextUtils.hasText(value)) {
			return 0;
		}
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			String msg = MessageUtil.getMessage("common.invalid_number",
					new Object[]{value});
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);

			throw new ConverterException(message);
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent comp, Object param)
			throws ConverterException {
		if (!TextUtils.hasText(param.toString())) {
			return "";
		}
		Long value = 0L;
		try {
			value = Long.valueOf(param.toString());
		} catch (Exception e) {
		}
		return ((value == null || ZERO.equals(value)) ? "" : value.toString());
	}
}