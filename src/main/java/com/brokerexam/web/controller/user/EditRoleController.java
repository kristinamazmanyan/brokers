package com.brokerexam.web.controller.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.user.Permission;
import com.brokerexam.domain.user.Role;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.auth.Permissions;
import com.brokerexam.web.util.MessageUtil;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brokerexam.web.util.JsfUtils;

@Controller("editRoleController")
@Scope("view")
public class EditRoleController extends BaseUserController implements
		Serializable {

	private static final long serialVersionUID = -2612800072631015326L;

	private static final Logger LOG = LoggerFactory
			.getLogger(EditRoleController.class);

	@Autowired
	private transient UserDetails userDetails;

	private Role role;

	private DualListModel<Permission> permissionsDualListModel;

	@PostConstruct
	public void init() {
		userDetails.check(Permissions.MANAGE_USER);

		try {
			String roleIdStr = JsfUtils.getRequestParam("id");
			long roleId = Long.valueOf(roleIdStr);
			role = roleService.getRole(roleId);
			List<Permission> permissions = roleService.getPermissions();
			List<Permission> assignedPermissions = role.getPermissions();
			permissions.removeAll(assignedPermissions);

			permissionsDualListModel = new DualListModel<Permission>(
					permissions, assignedPermissions);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void onSaveRole(ActionEvent event) {
		userDetails.check(Permissions.MANAGE_USER);

		try {
			role.setName(role.getName().trim());

			if (TextUtils.isEmpty(role.getName())) {
				MessageUtil.addErrorUsingKey("error.fill_required_fields");
				return;
			}
			if (roleService.roleExists(role.getName(), role.getId())) {
				MessageUtil.addErrorUsingKey("common.error_unique_name");
				return;
			}
			List<Permission> assignedPermissions = permissionsDualListModel
					.getTarget();
			Date currentDate = new Date();
			long userId = userDetails.getId();
			role.setChangedBy(userId);
			role.setChangedAt(currentDate);
			role.setPermissions(assignedPermissions);
			roleService.saveRole(role);

			String infoMessage = MessageUtil.getMessage("user.update_role.msg");
			LOG.debug(infoMessage);
			MessageUtil.addMessage(infoMessage);

			EventLog.getInstance().write(EventType.UPDATE_ROLE, role.getName(),
					userId, role.getId());
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public DualListModel<Permission> getPermissionsDualListModel() {
		return permissionsDualListModel;
	}

	public void setPermissionsDualListModel(
			DualListModel<Permission> permissionsDualListModel) {
		this.permissionsDualListModel = permissionsDualListModel;
	}

}
