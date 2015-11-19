package com.brokerexam.web.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.brokerexam.domain.user.Permission;

public class PermissionConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Permission permission = new Permission();
		String id = value.substring(0, value.indexOf("|"));
		String name = value.substring(value.indexOf("|") + 1,
				value.lastIndexOf("|"));
		String description = value.substring(value.lastIndexOf("|") + 1);
		permission.setId(Long.valueOf(id));
		permission.setName(name);
		permission.setDescription(description);
		return permission;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		Permission permission = (Permission) obj;
		return permission.getId() + "|" + permission.getName() + "|"
				+ permission.getDescription();
	}

}
