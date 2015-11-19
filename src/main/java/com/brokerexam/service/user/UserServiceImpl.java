package com.brokerexam.service.user;

import com.brokerexam.common.security.InformationEncryptor;
import com.brokerexam.domain.Base;
import com.brokerexam.domain.user.User;
import com.brokerexam.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	@Override
	public User getUserByName(String name) throws Exception {
		return userDao.getUserByName(name);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void login(long userId) throws Exception {
		userDao.login(userId);
	}


	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public boolean canDeleteUser(long userId) {
		return true; // TODO check
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void deleteUser(long id, long userId) {
		userDao.deleteUser(id, userId);
	}

	@Override
	public boolean loginExists(String login, long id) {
		return userDao.loginExists(login, id);
	}

	@Override
	public boolean emailExists(String email, long id) {
		return userDao.emailExists(email, id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void saveUser(User user) {
		if (user.getId() == 0) {
			String encryptedPasssword = InformationEncryptor
					.getEncryptedText(user.getPassword());
			user.setPassword(encryptedPasssword);
		}
		userDao.saveUser(user);
	}

	@Override
	public User getUser(long userId) {
		return userDao.getUser(userId);
	}

	@Override
	public List<Base> lookupUsers() {
		return userDao.lookupUsers();
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void changePassword(String newPassword, long userId) {
		String encryptedPassword = InformationEncryptor
				.getEncryptedText(newPassword);
		userDao.changePassword(encryptedPassword, userId);
	}

}
