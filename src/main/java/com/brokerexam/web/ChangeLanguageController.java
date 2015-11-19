package com.brokerexam.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brokerexam.common.i18n.I18NMap;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.web.util.SessionUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChangeLanguageController implements Serializable {

	private static final long serialVersionUID = -1648233849298348938L;

	@Autowired
	@Qualifier("i18n")
	private I18NMap i18n;

	@RequestMapping(method = RequestMethod.POST, value = { "/changeLanguage.action" })
	public void changeLanguage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String langCode = request.getParameter("langCode");

		try {

			if (!TextUtils.isEmpty(langCode)) {
				Locale locale = i18n.getLocaleByLanguage(langCode);

				if (locale != null) {
					SessionUtils.putLocale(locale, request.getSession(true));
					SessionUtils
							.putLanguage(langCode, request.getSession(true));
				}
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
		}
	}
}
