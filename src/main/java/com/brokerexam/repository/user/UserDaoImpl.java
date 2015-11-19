package com.brokerexam.repository.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.brokerexam.domain.Base;
import com.brokerexam.domain.BaseObj;
import com.brokerexam.domain.user.Permission;
import com.brokerexam.domain.user.Role;
import com.brokerexam.domain.user.User;
import com.brokerexam.repository.AbstractDaoImpl;
import com.brokerexam.repository.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("userDao")
@Lazy
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {
	private UserMapper userMapper = new UserMapper();
	private RoleMapper roleMapper = new RoleMapper();
	private RowMapper<User> userLoginMapper = new RowMapper<User>() {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong(DBUtils.id));
			user.setLogin(rs.getString(UserMapper.LOGIN));
			user.setPassword(rs.getString(UserMapper.PASSWORD));
			user.setFullName(rs.getString(UserMapper.FULL_NAME));
			user.setEmail(rs.getString(UserMapper.EMAIL));
			user.setPhone(rs.getString(UserMapper.PHONE));
			user.setLastLogin(rs.getTimestamp(UserMapper.LAST_LOGIN));

			return user;
		}
	};
	private RowMapper<Base> lookupUserMapper = new RowMapper<Base>() {

		public Base mapRow(ResultSet rs, int rowNum) throws SQLException {
			Base user = new BaseObj();
			user.setId(rs.getLong(DBUtils.id));
			user.setName(rs.getString(UserMapper.LOGIN));
			return user;
		}
	};
	private PermissionMapper permissionMapper = new PermissionMapper();
	
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Autowired
	@Qualifier("permissionDao")
	private PermissionDao permissionDao;

	@Autowired
	UserDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static final String getUserByNameSql = "select * from user u where u.login = :login and u.deleted = false";

	@Override
	public User getUserByName(String name) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put(UserMapper.LOGIN, name);

		User user = (User) parameterJdbcTemplate.queryForObject(
				getUserByNameSql, params, userLoginMapper);

		user.setPermissions(getUserPermissions(user.getId()));
		return user;
	}

	private static final String getUserPermissionsSql = "select distinct  p.* from user_roles ur "
			+ "inner join role r on r.id = ur.role_id "
			+ "inner join role_permissions rp on r.id = rp.role_id "
			+ "inner join permission p on rp.permission_id = p.id where ur.user_id = :id";

	@SuppressWarnings("unchecked")
	private List<Permission> getUserPermissions(long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DBUtils.id, userId);
		return parameterJdbcTemplate.query(getUserPermissionsSql, params,
				permissionMapper);
	}

	private static final String loginSql = "update user set last_login = :last_login where id = :id";

	@Override
	public void login(long userId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DBUtils.id, userId);
		params.put(UserMapper.LAST_LOGIN,
				new Timestamp(System.currentTimeMillis()));
		parameterJdbcTemplate.update(loginSql, params);
	}	

	private static final String getUsersSql = "select * from user where deleted = false";

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		List<User> users = getJdbcTemplate().query(getUsersSql, userMapper);
		return users;
	}

	private static final String deleteUserSql = "update user set changed_by = :changed_by, changed_at = :changed_at, deleted = true where id = :id";

	@Override
	public void deleteUser(long id, long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DBUtils.id, id);
		params.put(DBUtils.changed_by, userId);
		params.put(DBUtils.changed_at,
				new Timestamp(System.currentTimeMillis()));
		parameterJdbcTemplate.update(deleteUserSql, params);
	}

	private static final String loginExistsSql = "select id from user where LOWER(login) = ? and id != ?";

	@Override
	public boolean loginExists(String login, long id) {
		List<Long> list = getJdbcTemplate().queryForList(loginExistsSql,
				new Object[] { login.toLowerCase(), id }, Long.class);
		return list.size() > 0;
	}

	private static final String emailExistsSql = "select id from user where email = ? and id != ?";

	@Override
	public boolean emailExists(String email, long id) {
		List<Long> list = getJdbcTemplate().queryForList(emailExistsSql,
				new Object[] { email, id }, Long.class);
		return list.size() > 0;
	}

	private static final String createUserSql = "insert into user "
			+ "(id, login, password, full_name, email, "
			+ "phone, created_by, created_at, changed_by, changed_at, deleted) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String updateUserSql = "update user "
			+ "set login = :login, full_name = :full_name, "
			+ "email = :email, phone = :phone, changed_by = :changed_by, "
			+ "changed_at = :changed_at where id = :id";

	@Override
	public void saveUser(User user) {
		long userId = user.getId();

		if (userId == 0) {
			userId = DBUtils.getNextIndex(getJdbcTemplate(), "user");
			user.setId(userId);
			getJdbcTemplate().update(
					createUserSql,
					new Object[] { user.getId(), user.getLogin(),
							user.getPassword(), user.getFullName(),
							user.getEmail(), user.getPhone(),
							user.getCreatedBy(), user.getCreatedAt(),
							user.getChangedBy(), user.getChangedAt(), false });
			createUserRoles(user);
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(DBUtils.id, user.getId());
			params.put(UserMapper.LOGIN, user.getLogin());
			params.put(UserMapper.FULL_NAME, user.getFullName());
			params.put(UserMapper.EMAIL, user.getEmail());
			params.put(UserMapper.PHONE, user.getPhone());
			params.put(DBUtils.changed_by, user.getChangedBy());
			params.put(DBUtils.changed_at, user.getChangedAt());
			parameterJdbcTemplate.update(updateUserSql, params);
			updateUserRoles(user);
		}
	}

	private void updateUserRoles(User user) {
		deleteUserRoles(user.getId());
		createUserRoles(user);
	}

	private static final String deleteUserRolesSql = "delete from user_roles where user_id = ?";

	private void deleteUserRoles(long id) {
		getJdbcTemplate().update(deleteUserRolesSql, new Object[] { id });
	}

	private static final String createUserRolesSql = "insert into user_roles (user_id, role_id) VALUES(?, ?)";

	private void createUserRoles(final User user) {
		final List<Role> roles = user.getRoles();

		getJdbcTemplate().batchUpdate(createUserRolesSql,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setLong(1, user.getId());
						ps.setLong(2, roles.get(i).getId());
					}

					public int getBatchSize() {
						return roles.size();
					}
				});
	}
	
	private static final String getUserSql = "select * from user u where u.id = :id and u.deleted = false";
	@Override
	public User getUser(long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DBUtils.id, userId);

		@SuppressWarnings("unchecked")
		User user = (User) parameterJdbcTemplate.queryForObject(
				getUserSql, params, userMapper);
		user.setRoles(getUserRoles(userId));		
		user.setPermissions(getUserPermissions(user.getId()));
		return user;
	}
	
	private static final String getUserRolesSql = "select distinct  r.* from user_roles ur "
			+ "inner join role r on r.id = ur.role_id "
			+ "where ur.user_id = :id";

	@SuppressWarnings("unchecked")
	private List<Role> getUserRoles(long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DBUtils.id, userId);
		return parameterJdbcTemplate.query(getUserRolesSql, params,
				roleMapper);
	}
	
	private static final String lookupUsersSql = "select u.id, u.login from user u order by u.login";
	@Override
	public List<Base> lookupUsers() {
		List<Base> result = (List<Base>) getJdbcTemplate().query(lookupUsersSql,
				lookupUserMapper);
		return result;
	}
	
	private static final String changePasswordSql = "update user set changed_by = :changed_by, "
			+ "changed_at = :changed_at, password = :password where id = :id";
	@Override
	public void changePassword(String encryptedPassword, long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DBUtils.id, userId);
		params.put(UserMapper.PASSWORD, encryptedPassword);
		params.put(DBUtils.changed_by, userId);
		params.put(DBUtils.changed_at,
				new Timestamp(System.currentTimeMillis()));
		parameterJdbcTemplate.update(changePasswordSql, params);
	}

}
