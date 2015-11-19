package com.brokerexam.repository.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.brokerexam.domain.user.User;
import org.springframework.jdbc.core.RowMapper;

import com.brokerexam.repository.DBUtils;

public class UserMapper implements RowMapper {
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String FULL_NAME = "full_name";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String LAST_LOGIN = "last_login";
	

	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(DBUtils.id));
		user.setLogin(rs.getString(LOGIN));
		user.setFullName(rs.getString(FULL_NAME));
		user.setEmail(rs.getString(EMAIL));
		user.setPhone(rs.getString(PHONE));
		user.setLastLogin(rs.getTimestamp(LAST_LOGIN));
		
		return user;
	}
}
