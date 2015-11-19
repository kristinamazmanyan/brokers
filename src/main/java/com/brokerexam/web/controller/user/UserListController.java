package com.brokerexam.web.controller.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.user.User;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.auth.Permissions;
import com.brokerexam.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brokerexam.web.util.JsfUtils;

@Controller("userListController")
@Scope("view")
public class UserListController extends BaseUserController implements
		Serializable {
	
	private static final long serialVersionUID = 540515037703467200L;

	private static final Logger LOG = LoggerFactory
			.getLogger(UserListController.class);
	
	@Autowired
	private transient UserDetails userDetails;
	
	private List<User> users;
	
	@PostConstruct
	public void init() {
		userDetails.check(Permissions.VIEW_USERS);
		users = userService.getUsers();
		dataCount = users.size();
	}

	public void deleteUser(ActionEvent event) {
		userDetails.check(Permissions.MANAGE_USER);
		
		try {
			String userStr = JsfUtils.getRequestParam("userId");
			
			if (!TextUtils.hasText(userStr)) {
				return;
			}

			Long userId = Long.valueOf(userStr);
			
			if (!userService.canDeleteUser(userId)) {
				MessageUtil.addErrorUsingKey("user.user_delete_error");
				return;
			}
			
			User userToBeDeleted = userService.getUser(userId);

			userService.deleteUser(userId, userDetails.getId());

			String infoMessage = MessageUtil.getMessage("user.delete_user.msg");
			LOG.debug(infoMessage);
			MessageUtil.addMessage(infoMessage);

			users = userService.getUsers();
			dataCount = users.size();

			EventLog.getInstance().write(EventType.DELETE_USER,
					userToBeDeleted.getLogin(), userDetails, userId);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}		
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
