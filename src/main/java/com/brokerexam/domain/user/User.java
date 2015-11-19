package com.brokerexam.domain.user;

import com.brokerexam.domain.Persistent;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Persistent implements Serializable {
	private static final long serialVersionUID = -523127442416140736L;

	private String login;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private Date lastLogin = null;
	private List<Role> roles;
	private Map<String, String> permissions = new HashMap<String, String>(0);

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Map<String, String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, String> permissions) {
		this.permissions = permissions;
	}

	public void setPermissions(List<Permission> permissionList) {
		for (Permission permission : permissionList) {
			permissions.put(permission.getName(), permission.getName());
		}
	}

	public boolean hasPermission(String permissionName) {
		return permissions.containsKey(permissionName);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
