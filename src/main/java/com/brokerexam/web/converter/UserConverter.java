package com.brokerexam.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.brokerexam.domain.user.User;

public class UserConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		User user = new User();
		String id = value.substring(0, value.indexOf("|"));
		String login = value.substring(value.indexOf("|") + 1,
				value.lastIndexOf("|"));
		String fullName = value.substring(value.lastIndexOf("|") + 1);
		user.setId(Long.valueOf(id));
		user.setLogin(login);
		user.setFullName(fullName);
		return user;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		User user = (User) obj;
		return user.getId() + "|" + user.getLogin() + "|"
				+ user.getFullName();
	}

}
