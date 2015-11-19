package com.brokerexam.web.controller.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import com.brokerexam.web.auth.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.user.Role;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.util.JsfUtils;
import com.brokerexam.web.util.MessageUtil;

@Controller("roleListController")
@Scope("view")
public class RoleListController extends BaseUserController implements
		Serializable {

	private static final long serialVersionUID = -7821293867511300857L;

	private static final Logger LOG = LoggerFactory
			.getLogger(RoleListController.class);

	@Autowired
	private transient UserDetails userDetails;

	private List<Role> roles;

	@PostConstruct
	public void init() {
		userDetails.check(Permissions.VIEW_USERS);
		roles = roleService.getRoles();
		dataCount = roles.size();
	}

	public void deleteRole(ActionEvent event) {
		userDetails.check(Permissions.MANAGE_USER);

		String roleStr = JsfUtils.getRequestParam("roleId");

		if (!TextUtils.hasText(roleStr)) {
			return;
		}

		Long roleId = Long.valueOf(roleStr);

		if (!roleService.canDeleteRole(roleId)) {
			MessageUtil.addErrorUsingKey("user.role_delete_error");
			return;
		}
		Role roleToBeDeleted = roleService.getRole(roleId);
		roleService.deleteRole(roleId);

		String infoMessage = MessageUtil.getMessage("user.delete_role.msg");
		LOG.debug(infoMessage);
		MessageUtil.addMessage(infoMessage);

		roles = roleService.getRoles();
		dataCount = roles.size();

		EventLog.getInstance().write(EventType.DELETE_ROLE,
				roleToBeDeleted.getName(), userDetails, roleId);
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
