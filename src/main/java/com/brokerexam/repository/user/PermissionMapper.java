package com.brokerexam.repository.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.brokerexam.domain.user.Permission;
import com.brokerexam.repository.DBUtils;

public class PermissionMapper implements RowMapper {
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Permission dto = new Permission();
		dto.setId(rs.getLong(DBUtils.id));
		dto.setName(rs.getString(DBUtils.name));
		dto.setDescription(rs.getString(DBUtils.description));
		return dto;
	}
}
