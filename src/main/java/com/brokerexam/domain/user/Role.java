package com.brokerexam.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.brokerexam.domain.Descriptive;

public class Role extends Descriptive implements Serializable {

	private static final long serialVersionUID = 2287758714170478285L;

	private List<Permission> permissions = new ArrayList<Permission>(0);

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}
}
