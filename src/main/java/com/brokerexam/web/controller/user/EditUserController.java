package com.brokerexam.web.controller.user;

import com.brokerexam.web.auth.Permissions;
import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.user.Role;
import com.brokerexam.domain.user.User;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.util.JsfUtils;
import com.brokerexam.web.util.MessageUtil;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Controller("editUserController")
@Scope("view")
public class EditUserController extends BaseUserController implements
		Serializable {

	private static final long serialVersionUID = -6655900957901452205L;

	private static final Logger LOG = LoggerFactory
			.getLogger(EditUserController.class);

	@Autowired
	private transient UserDetails userDetails;

	private User user;
	private DualListModel<Role> rolesDualListModel;

	@PostConstruct
	public void init() {
		userDetails.check(Permissions.MANAGE_USER);
		
		try {
			String userIdStr = JsfUtils.getRequestParam("id");
			long userId = Long.valueOf(userIdStr);
			user = userService.getUser(userId);

			List<Role> roles = roleService.getRoles();
			List<Role> assignedRoles = user.getRoles();
			roles.removeAll(assignedRoles);
			rolesDualListModel = new DualListModel<Role>(roles, assignedRoles);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void onSaveUser(ActionEvent event) {
		userDetails.check(Permissions.MANAGE_USER);

		try {
			user.setLogin(user.getLogin().trim());

			if (TextUtils.isEmpty(user.getLogin())) {
				MessageUtil.addErrorUsingKey("error.fill_required_fields");
				return;
			}

			if (userService.loginExists(user.getLogin(), user.getId())) {
				MessageUtil.addErrorUsingKey("user.error_unique_login");
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
			long userId = userDetails.getId();
			user.setChangedBy(userId);
			user.setChangedAt(currentDate);
			user.setRoles(assignedRoles);
			userService.saveUser(user);

			String infoMessage = MessageUtil.getMessage("user.update_user.msg");
			LOG.debug(infoMessage);
			MessageUtil.addMessage(infoMessage);

			EventLog.getInstance().write(EventType.UPDATE_USER,
					user.getLogin(), userId, user.getId());
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

}
