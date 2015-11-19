package com.brokerexam.web.controller.user;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.user.Role;
import com.brokerexam.domain.user.User;
import com.brokerexam.web.auth.Permissions;
import com.brokerexam.web.util.MessageUtil;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.util.JsfUtils;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("createUserController")
@Scope("view")
public class CreateUserController extends BaseUserController implements
		Serializable {

	private static final long serialVersionUID = 4968931367507621892L;

	private static final Logger LOG = LoggerFactory
			.getLogger(CreateUserController.class);

	@Autowired
	private transient UserDetails userDetails;

	private String rePassword;
	private User user;
	private DualListModel<Role> rolesDualListModel;

	@PostConstruct
	public void init() {
		userDetails.check(Permissions.MANAGE_USER);
		
		user = new User();
		List<Role> roles = roleService.getRoles();
		List<Role> assignedRoles = new ArrayList<Role>();
		rolesDualListModel = new DualListModel<Role>(roles, assignedRoles);

		rePassword = null;
	}

	public void onCreateUser(ActionEvent event) {
		userDetails.check(Permissions.MANAGE_USER);

		try {
			user.setLogin(user.getLogin().trim());
			rePassword = rePassword.trim();
			user.setPassword(user.getPassword().trim());

			if (TextUtils.isEmpty(user.getLogin())
					|| TextUtils.isEmpty(user.getPassword())
					|| TextUtils.isEmpty(rePassword)) {
				MessageUtil.addErrorUsingKey("error.fill_required_fields");
				return;
			}

			if (userService.loginExists(user.getLogin(), user.getId())) {
				MessageUtil.addErrorUsingKey("user.error_unique_login");
				return;
			}

			if (!rePassword.equals(user.getPassword())) {
				MessageUtil
						.addErrorUsingKey("common.passwords_dont_match.error");
				return;
			}

			if (TextUtils.isNotEmpty(user.getEmail())) {
				if (userService.emailExists(user.getEmail(), user.getId())) {
					MessageUtil.addErrorUsingKey("common.error_unique_email");
					return;
				}
			}

			List<Role> assignedRoles = rolesDualListModel.getTarget();

			Date currentDate = new Date();
			long userId = JsfUtils.getUser().getId();
			user.setCreatedBy(userId);
			user.setCreatedAt(currentDate);
			user.setChangedBy(userId);
			user.setChangedAt(currentDate);
			user.setRoles(assignedRoles);
			userService.saveUser(user);

			String infoMessage = MessageUtil.getMessage("user.create_user.msg");
			LOG.debug(infoMessage);
			MessageUtil.addMessage(infoMessage);

			EventLog.getInstance().write(EventType.CREATE_USER,
					user.getLogin(), userId, user.getId());

			init();
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DualListModel<Role> getRolesDualListModel() {
		return rolesDualListModel;
	}

	public void setRolesDualListModel(DualListModel<Role> rolesDualListModel) {
		this.rolesDualListModel = rolesDualListModel;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
