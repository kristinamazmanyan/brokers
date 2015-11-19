package com.brokerexam.service.user;

import java.util.List;

import com.brokerexam.domain.user.Permission;
import com.brokerexam.domain.user.Role;

public interface RoleService {
	List<Role> getRoles();
	List<Permission> getPermissions();
	void saveRole(Role role);
	boolean roleExists(String name, long id);
	boolean canDeleteRole(long id);
	void deleteRole(long id);
	Role getRole(long id);
}
