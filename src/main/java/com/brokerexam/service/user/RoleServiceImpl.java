package com.brokerexam.service.user;

import java.util.List;

import com.brokerexam.domain.user.Permission;
import com.brokerexam.domain.user.Role;
import com.brokerexam.repository.user.PermissionDao;
import com.brokerexam.repository.user.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class RoleServiceImpl implements RoleService {

	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;

	@Autowired
	@Qualifier("permissionDao")
	private PermissionDao permissionDao;

	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	public List<Permission> getPermissions() {
		return permissionDao.getPermissions();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void saveRole(Role role) {
		roleDao.saveRole(role);
	}

	@Override
	public boolean roleExists(String name, long id) {
		return roleDao.roleExists(name, id);
	}

	@Override
	public boolean canDeleteRole(long id) {
		return roleDao.canDeleteRole(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void deleteRole(long id) {
		roleDao.deleteRole(id);
	}

	@Override
	public Role getRole(long id) {
		return roleDao.getRole(id);
	}

}
