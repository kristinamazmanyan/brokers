package com.brokerexam.web.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brokerexam.common.security.InformationEncryptor;
import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.user.User;
import com.brokerexam.service.user.UserService;
import com.brokerexam.web.util.MessageUtil;
import com.brokerexam.service.exam.ExamService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.web.beans.AppConfig;

@Controller("authController")
@Scope("request")
public class AuthController implements Serializable {

	private static final long serialVersionUID = 6991198865955216909L;
	
	@Autowired
	private transient UserDetails userDetails;

	@Autowired
	private transient UserService userService;

	@Autowired
	private transient ExamService examService;

	@Autowired
	private AppConfig appConfig;

	private String username;
	private String password;
	
	public void login(ActionEvent event) {

		if (TextUtils.isNotEmpty(username) && TextUtils.isNotEmpty(password)) {

			try {
				User user = userService.getUserByName(username);

				if (user != null) {

					if (InformationEncryptor.isSamePassword(user.getPassword(),
							password)) {
						Map<String, String> permissions = user.getPermissions();

						if (permissions.containsKey(Permissions.LOGIN)) {
							userService.login(user.getId());

							userDetails.setId(user.getId());
							userDetails.setLogin(user.getLogin());
							userDetails.setFullName(user.getFullName());
							userDetails.setPermissions(user.getPermissions());
							
							ExternalContext externalContext = FacesContext
									.getCurrentInstance().getExternalContext();
							externalContext.redirect("pages/index.xhtml");
						}
					}  else {
						MessageUtil.addError("Username or password is incorrect.");
					}
				} else {
					MessageUtil.addError("Username or password is incorrect.");
				}
			} catch (Exception e) {
				MessageUtil.addError("Username or password is incorrect.");
				LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
			}
		} else {
			MessageUtil.addError("Username or password is incorrect.");
		}
	}


	public void examMemberLogin(ActionEvent event) throws IOException {

		ExamMember examMember = examService.loginExamMember(username, password);

		if (examMember == null) {
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Code",
					"Some more detailed descriptin about passport and code");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
		else {
//			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
//					"Some more detailed descriptin about username and password");
//			FacesContext.getCurrentInstance().addMessage(null, fm);
//
			userDetails.setId(examMember.getId());
			userDetails.setLogin(examMember.getPassport());
			userDetails.setFullName(examMember.getFirstName() + " " + examMember.getLastName());
			userDetails.setPermissions(Collections.emptyMap());

			ExternalContext externalContext = FacesContext
					.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getApplicationContextPath() + "/pages/member-index.xhtml");
			externalContext.getSessionMap().put("examMember", examMember);


		}
	}



	@RequestMapping({ "/auth/logout.action" })
	public void doLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession(true).invalidate();
		response.sendRedirect(request.getContextPath()+"/");
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
