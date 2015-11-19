package com.brokerexam.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.brokerexam.domain.user.Role;

public class RoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Role role = new Role();
		String id = value.substring(0, value.indexOf("|"));
		String name = value.substring(value.indexOf("|") + 1,
				value.lastIndexOf("|"));
		String description = value.substring(value.lastIndexOf("|") + 1);
		role.setId(Long.valueOf(id));
		role.setName(name);
		role.setDescription(description);
		return role;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		Role role = (Role) obj;
		return role.getId() + "|" + role.getName() + "|"
				+ role.getDescription();
	}

}
