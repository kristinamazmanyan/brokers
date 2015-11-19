package com.brokerexam.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.brokerexam.domain.BaseObj;

public class BaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		BaseObj baseObj = new BaseObj();
		String id = value.substring(0, value.indexOf("|"));
		String name = value.substring(value.lastIndexOf("|") + 1);
		baseObj.setId(Long.valueOf(id));
		baseObj.setName(name);
		return baseObj;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		BaseObj baseObj = (BaseObj) obj;
		return baseObj.getId() + "|" + baseObj.getName();
	}

}
