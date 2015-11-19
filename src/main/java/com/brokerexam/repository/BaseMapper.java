package com.brokerexam.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.brokerexam.domain.BaseObj;

public class BaseMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		BaseObj baseObj = new BaseObj();
		baseObj.setId(rs.getLong(DBUtils.id));
		baseObj.setName(rs.getString(DBUtils.name));
		return baseObj;
	}

}
