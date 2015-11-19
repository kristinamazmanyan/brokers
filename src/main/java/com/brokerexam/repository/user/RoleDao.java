package com.brokerexam.repository.user;

import java.util.List;

import com.brokerexam.domain.user.Role;

public interface RoleDao {
	List<Role> getRoles();

	Role getRole(long id);

	Role getByName(String name);
	
	void saveRole(Role role);

	boolean canDeleteRole(long id);

	void deleteRole(long id);

	boolean roleExists(String name, long id);
}
