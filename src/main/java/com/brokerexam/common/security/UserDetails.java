package com.brokerexam.common.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.brokerexam.web.auth.Permissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.brokerexam.common.ex.AccessDeniedException;

@Component("userDetails")
@Scope("session")
public class UserDetails implements Serializable {
	private static final long serialVersionUID = -6698159751192148589L;
	private long id = 0;
	private String login = null;
	private String role = null;
	private String fullName = null;
	private Map<String, String> permissions = new HashMap<String, String>(0);

	public UserDetails(long id, String login) {
		super();
		this.id = id;
		this.login = login;
	}

	public UserDetails() {
		super();
	}

	public boolean hasPermission(String permissionName) {
		return permissions.containsKey(permissionName);
	}

	public boolean hasRole(String roleStr) {
		return roleStr.equals(role);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Map<String, String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, String> permissions) {
		this.permissions = permissions;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public boolean getViewSystem() {
		return permissions.containsKey(Permissions.VIEW_SYSTEM);
	}

	public boolean getViewUsers() {
		return permissions.containsKey(Permissions.VIEW_USERS);
	}

	public boolean getManageUser() {
		return permissions.containsKey(Permissions.MANAGE_USER);
	}

	public boolean getViewEvents() {
		return permissions.containsKey(Permissions.VIEW_EVENTS);
	}

	public boolean getViewExams() {
		return permissions.containsKey(Permissions.VIEW_EXAMS);
	}

	public boolean getManageExams() {
		return permissions.containsKey(Permissions.MANAGE_EXAMS);
	}

	public boolean getViewCommitteeMembers() {
		return permissions.containsKey(Permissions.VIEW_COMMITTEE_MEMBERS);
	}

	public boolean getManageCommitteeMembers() {
		return permissions.containsKey(Permissions.MANAGE_COMMITTEE_MEMBERS);
	}

	public boolean getViewExamMembers() {
		return permissions.containsKey(Permissions.VIEW_EXAM_MEMBERS);
	}

	public boolean getManageExamMembers() {
		return permissions.containsKey(Permissions.MANAGE_EXAM_MEMBERS);
	}

	public boolean getManageExamQuеstions() {
		return permissions.containsKey(Permissions.MANAGE_EXAM_QUESTIONS);
	}

	public boolean getPrintResult() {
		return permissions.containsKey(Permissions.PRINT_RESULT);
	}

	public boolean getViewSearch() {
		return permissions.containsKey(Permissions.VIEW_SEARCH);
	}






	public void check(String permission) {
		if (!hasPermission(permission)) {
			throw new AccessDeniedException();
		}
	}

}
