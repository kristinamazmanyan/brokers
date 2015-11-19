package com.brokerexam.repository.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.brokerexam.domain.user.Role;
import com.brokerexam.repository.DBUtils;

public class RoleMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setId(rs.getLong(DBUtils.id));
		role.setName(rs.getString(DBUtils.name));
		role.setDescription(rs.getString(DBUtils.description));
		return role;
	}
}
