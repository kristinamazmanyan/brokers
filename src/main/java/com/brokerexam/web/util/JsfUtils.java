package com.brokerexam.web.util;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.Constants;
import com.brokerexam.domain.exam.ExamMember;

public class JsfUtils {
	
	public static void redirect(String url) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().redirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void forward(String url) {
		try {
			 FacesContext.getCurrentInstance().getExternalContext().dispatch(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static void redirect(FacesContext facesContext, String url) {
		try {
			facesContext.getExternalContext().redirect(url);
		} catch (IOException e) {
		}
	}

	public static String getRequestParam(String name) {
		return getRequestMap().get(name);
	}

	public static Map<String, String> getRequestMap() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
	}

	public static final Object getSessionAttribute(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		return session.getAttribute(name);
	}

	public static void putSessionAttribute(String name, Object value) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		session.setAttribute(name, value);
	}

	public static final Object getSessionAttr(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		return session.getAttribute(name);
	}
	
	
	public static UserDetails getUser() {
		return (UserDetails) getSessionAttribute(Constants.SESSION_USER_DETAILS);
	}
	
	public static Locale getLocale() {
		return (Locale) getSessionAttribute(Constants.SESSION_LOCALE);
	}

	public static ExamMember getExamMember() {
		return (ExamMember) getSessionAttribute("examMember");
	}

	public static Integer getCompleted() {
		return (Integer) getSessionAttribute("completed");
	}
	
	
}
