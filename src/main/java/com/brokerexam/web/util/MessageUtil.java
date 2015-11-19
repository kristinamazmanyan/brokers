package com.brokerexam.web.util;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.brokerexam.common.i18n.I18NMap;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class MessageUtil {


	private static I18NMap i18Map;

	static {
		ApplicationContext ctx = FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance());
		i18Map = (I18NMap) ctx.getBean("i18n");
	}

	public static void addError(String msg) {
		addError(msg, "");
	}

	public static void addError(String msgStr, String detail) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msgStr, detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void addMultilineError(String title, List<String> messages) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title,
				null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		if (messages != null) {
			for (String currentMessage : messages) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								currentMessage, null));
			}
		}
	}

	public static void addWarning(String msg) {
		addWarning(msg, "");
	}

	public static void addWarning(String msgStr, String detail) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, msgStr,
				detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void addMessage(String msg) {
		addMessage(msg, "");
	}

	public static void addMessage(String msgStr, String detail) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, msgStr,
				detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static String getMessage(String key) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return i18Map.getMessage(key, null, request);
	}

	public static String getMessage(String key, Object... params) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		return i18Map.getMessage(key, params, request);
	}

	public static void addMessageUsingKey(String messageKey, Object... objects) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		addMessage(i18Map.getMessage(messageKey, objects, request));
	}

	public static void addErrorUsingKey(String messageKey, Object... objects) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		addError(i18Map.getMessage(messageKey, objects, request));
	}

	public static void addWarningUsingMessageKey(String messageKey,
			Object... objects) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		addWarning(i18Map.getMessage(messageKey, objects, request));
	}

}
