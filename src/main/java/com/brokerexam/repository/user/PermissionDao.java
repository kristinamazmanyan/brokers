package com.brokerexam.repository.user;

import java.util.List;

import com.brokerexam.domain.user.Permission;

public interface PermissionDao {

	List<Permission> getPermissionsByRoleId(long roleId);

	List<Permission> getRolePermissions(long roleId);

	List<Permission> getPermissions();
}
