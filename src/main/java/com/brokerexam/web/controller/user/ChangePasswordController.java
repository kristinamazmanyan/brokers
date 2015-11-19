package com.brokerexam.web.controller.user;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import com.brokerexam.common.security.InformationEncryptor;
import com.brokerexam.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.util.JsfUtils;
import com.brokerexam.web.util.MessageUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("changePasswordController")
@Scope("view")
public class ChangePasswordController extends BaseUserController implements
		Serializable {

	private static final Logger LOG = LoggerFactory
			.getLogger(ChangePasswordController.class);

	private String oldPassword;
	private String newPassword;
	private String rePassword;

	public void init() {
		JsfUtils.getUser();
	}

	public void changePassword(ActionEvent event) {
		try {
			UserDetails userDetails = JsfUtils.getUser();
			long userId = userDetails.getId();

			if (!TextUtils.hasText(oldPassword)
					|| !TextUtils.hasText(newPassword)
					|| !TextUtils.hasText(rePassword)) {
				MessageUtil.addErrorUsingKey("error.fill_required_fields");
				return;
			}

			User dbUser = userService.getUserByName(userDetails.getLogin());

			if (dbUser != null && dbUser.getId() == userDetails.getId()) {
				if (!InformationEncryptor.isSamePassword(dbUser.getPassword(),
						oldPassword)) {
					MessageUtil.addErrorUsingKey("user.old_password_incorrect");
					return;
				} else {
					if (!rePassword.equals(newPassword)) {
						MessageUtil
								.addErrorUsingKey("user.passwords_does_not_match");
						return;
					}
					userService.changePassword(newPassword, dbUser.getId());

					EventLog.getInstance().write(EventType.CHANGE_PASSWORD,
							dbUser.getLogin(), userId, dbUser.getId());
				}
			}
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
