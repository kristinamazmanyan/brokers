package com.brokerexam.service.user;

import java.util.List;

import com.brokerexam.domain.Base;
import com.brokerexam.domain.user.User;

public interface UserService {
	User getUserByName(String name)throws Exception;

	void login(long userId)throws Exception;

	List<User> getUsers();

	boolean canDeleteUser(long userId);

	void deleteUser(long id, long userId);

	boolean loginExists(String login, long id);

	boolean emailExists(String email, long id);

	void saveUser(User user);

	User getUser(long userId);

	List<Base> lookupUsers();

	void changePassword(String newPassword, long id);
}
