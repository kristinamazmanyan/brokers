package com.brokerexam.bean.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brokerexam.domain.user.Role;

public class UserBean implements Serializable {
	private static final long serialVersionUID = -235650858815064480L;

	private long id = 0;
	private long createdBy = 0;
	private Date createdAt = null;
	private long changedBy = 0;
	private Date changedAt = null;

	private String login;
	private String password;
	private String rePassword;
	private String fullName;
	private String email;
	private String phone;
	private Date lastLogin = null;
	private List<Role> roles;
	private Map<String, String> permissions = new HashMap<String, String>(0);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(long changedBy) {
		this.changedBy = changedBy;
	}

	public Date getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}

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

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Map<String, String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, String> permissions) {
		this.permissions = permissions;
	}
}
