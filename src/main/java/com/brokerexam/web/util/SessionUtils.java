package com.brokerexam.web.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.Constants;

public class SessionUtils {

	public static void putUserDetails(UserDetails userDetails,
			HttpSession session) {
		session.setAttribute(Constants.SESSION_USER_DETAILS, userDetails);
	}

	public static void invalidateSession(HttpServletRequest aHttpServletRequest) {
		aHttpServletRequest.getSession().setAttribute(
				Constants.SESSION_USER_DETAILS, null);
		aHttpServletRequest.getSession().invalidate();
	}

	public static UserDetails getUserDetails(HttpSession session) {
		if (session == null)
			return null;
		return (UserDetails) session
				.getAttribute(Constants.SESSION_USER_DETAILS);
	}

	public static UserDetails getUserDetails(HttpServletRequest request) {
		return getUserDetails(request.getSession(false));
	}

	public static Locale getLocale(HttpSession session) {
		return (Locale) session.getAttribute(Constants.SESSION_LOCALE);
	}

	public static void putLanguage(String language, HttpSession session) {
		session.setAttribute(Constants.SESSION_LANGUAGE, language);
	}

	public static void putLocale(Locale locale, HttpSession session) {
		session.setAttribute(Constants.SESSION_LOCALE, locale);
	}

}
