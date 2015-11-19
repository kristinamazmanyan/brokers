package com.brokerexam.repository.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import com.brokerexam.domain.user.Permission;
import com.brokerexam.repository.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("permissionDao")
@Lazy
public class PermissionDaoImpl extends AbstractDaoImpl implements PermissionDao {
	
	@Autowired
	PermissionDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static final String getPermissionsByRoleIdSql = "select p.* "
			+ "from permission p "
			+ "join role_permissions rp on p.id = rp.permission_id  "
			+ "where rp.role_id = ?";

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissionsByRoleId(long roleId) {
		try {
			List<Permission> perm = getJdbcTemplate().query(
					getPermissionsByRoleIdSql, new Object[] { roleId },
					new PermissionMapper());
			return perm;
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getRolePermissions(long roleId) {
		try {
			return getJdbcTemplate().query(getPermissionsByRoleIdSql,
					new Object[] { roleId }, new RowMapper() {

						@Override
						public Object mapRow(ResultSet rs, int arg1)
								throws SQLException {
							Permission dto = new Permission();
							dto.setId(rs.getInt("ID"));
							dto.setName(rs.getString("NAME"));
							dto.setDescription(rs.getString("description"));
							return dto;
						}

					});
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	private static final String getPermissionsSql = "select * from permission order by name";

	@Override
	@SuppressWarnings("unchecked")
	public List<Permission> getPermissions() {
		return getJdbcTemplate().query(getPermissionsSql,
				new PermissionMapper());
	}
}
