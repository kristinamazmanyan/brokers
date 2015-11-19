package com.brokerexam.repository.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.brokerexam.domain.user.Permission;
import com.brokerexam.domain.user.Role;
import com.brokerexam.repository.AbstractDaoImpl;
import com.brokerexam.repository.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
@Lazy
public class RoleDaoImpl extends AbstractDaoImpl implements RoleDao {
	
	@Autowired
	@Qualifier("permissionDao")
	private PermissionDao permissionDao;
	private RoleMapper roleMapper = new RoleMapper();
	
	@Autowired
	RoleDaoImpl(DataSource dataSource) {
		super(dataSource);
	}
	
	private static final String getRoleSql = "select * from role where id = ?";

	@Override
	public Role getRole(long id) {
		Role role = (Role) getJdbcTemplate().queryForObject(getRoleSql,
				new Object[] { id }, roleMapper);
		List<Permission> permissions = permissionDao
				.getPermissionsByRoleId(role.getId());
		role.setPermissions(permissions);
		return role;
	}

	private static final String getByNameSql = "select * from role where name = ?";

	@Override
	public Role getByName(String name) {
		Role role = (Role) getJdbcTemplate().queryForObject(getByNameSql,
				new Object[] { name }, new RoleMapper());
		List<Permission> permissions = permissionDao
				.getPermissionsByRoleId(role.getId());
		role.setPermissions(permissions);
		return role;
	}


	private static final String getRolesSql = "select * from role order by name";

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		List<Role> result = (List<Role>) getJdbcTemplate().query(getRolesSql,
				roleMapper);
		return result;
	}

	private static final String createRoleSql = "insert into role (id, name, description, created_by, created_at, changed_by, changed_at) VALUES (?, ?, ?, ?, ?, ? , ?)";

	private static final String updateRoleSql = "update role set name = ?, description = ?, changed_by = ?, changed_at = ?  where id = ?";

	@Override
	public void saveRole(Role role) {
		if (role.getId() == 0) {
			long id = DBUtils.getNextIndex(getJdbcTemplate(), "role");
			getJdbcTemplate().update(
					createRoleSql,
					new Object[] { id, role.getName(), role.getDescription(),
							role.getCreatedBy(),
							new java.sql.Date(role.getCreatedAt().getTime()),
							role.getChangedBy(),
							new java.sql.Date(role.getChangedAt().getTime()) });
			role.setId(id);
			saveRolePermissions(role);
		} else {
			getJdbcTemplate().update(
					updateRoleSql,
					new Object[] { role.getName(), role.getDescription(),
							role.getChangedBy(),
							new java.sql.Date(role.getChangedAt().getTime()),
							role.getId() });
			deleteRolepermissions(role.getId());
			saveRolePermissions(role);
		}
	}

	private static final String saveRolePermissionsSql = "insert into role_permissions (role_id, permission_id) VALUES (?, ?)";

	private void saveRolePermissions(final Role role) {
		final List<Permission> permissions = role.getPermissions();
		getJdbcTemplate().batchUpdate(saveRolePermissionsSql,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setLong(1, role.getId());
						ps.setLong(2, role.getPermissions().get(i).getId());
					}

					public int getBatchSize() {
						return permissions.size();
					}
				});
	}

	private static final String canDeleteRoleSql = "select user_id  from user_roles ur where ur.role_id = ?";

	@Override
	@SuppressWarnings("unchecked")
	public boolean canDeleteRole(long id) {
		List<Long> list = getJdbcTemplate().queryForList(canDeleteRoleSql,
				new Object[] { id }, Long.class);
		if (list.size() > 0) {
			return false;
		}
		return true;
	}

	private static final String deleteRolepermissionsSql = "delete from role_permissions where role_id = ?";

	private void deleteRolepermissions(long roleId) {
		getJdbcTemplate().update(deleteRolepermissionsSql,
				new Object[] { roleId });
	}

	private static final String deleteRoleSql = "delete from role where id = ?";

	@Override
	public void deleteRole(long id) {
		deleteRolepermissions(id);
		getJdbcTemplate().update(deleteRoleSql, new Object[] { id });

	}

	private static final String roleExistsSql = "select id from role where LOWER(name) = ? and id != ?";

	@SuppressWarnings("unchecked")
	@Override
	public boolean roleExists(String name, long id) {
		List<Long> list = getJdbcTemplate().queryForList(roleExistsSql,
				new Object[] { name.toLowerCase(), id }, Long.class);
		return list.size() > 0;
	}

}
