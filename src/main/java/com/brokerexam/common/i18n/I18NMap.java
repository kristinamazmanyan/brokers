package com.brokerexam.common.i18n;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.brokerexam.web.util.JsfUtils;
import com.brokerexam.web.util.SessionUtils;

@Component("i18n")
public class I18NMap extends DummyMap implements InitializingBean {

	private static final String propertiesFilePath = "i18n";
	
	@Value("#{appHomeProvider.appHome}")
	private String appHome;
	private Map<String, Map<String, String>> messages = new HashMap<String, Map<String, String>>();
	
	private Locale defaultLocale;
	
	private List<Locale> supportedLocales = new ArrayList<Locale>();

	public String get(Object key) {
		Locale locale = JsfUtils.getLocale();
		return get(key, locale);
	}
	
	public String get(Object key, Object params[]) {
		Locale locale = JsfUtils.getLocale();
		
		String text = get(key, locale);
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	public String get(Object key, Locale locale) {
		if (locale == null) {
			locale = defaultLocale;
		}

		Map<String, String> localeMessages = messages.get(locale.getLanguage());

		if (localeMessages == null) {
			localeMessages = messages.get(defaultLocale.getLanguage());
		}

		String value = localeMessages.get(key);

		if (value == null) {
			value = key.toString();
		}
		return value;
	}

	public String getMessageDefault(String key, Object params[]) {
		String text = get(key, defaultLocale);
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, defaultLocale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	public String getMessage(String key, Object params[],
			HttpServletRequest request) {
		Locale locale = defaultLocale;
		
		if (SessionUtils.getUserDetails(request) != null) {
			locale = SessionUtils.getLocale(request.getSession());
			
			if (locale == null) {
				locale = defaultLocale;
				
				SessionUtils.putLocale(locale, request.getSession());
			}
		}
		String text = get(key, locale);

		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	public boolean isEmpty() {
		return false;
	}
	

	public void afterPropertiesSet() throws Exception {
		defaultLocale = new Locale("hy", "AM");
		supportedLocales = new ArrayList<Locale>();
		supportedLocales.add(defaultLocale);
		supportedLocales.add(new Locale("en"));
				
		for (Locale locale : supportedLocales) {
			Properties bundle = new Properties();
			StringBuilder filePath = new StringBuilder();
			filePath.append(appHome).append(File.separator);
			filePath.append(propertiesFilePath).append(File.separator);
			filePath.append("messages_").append(locale.getLanguage())
					.append(".properties");
			bundle.load(new InputStreamReader(new FileInputStream(filePath
					.toString()), "UTF-8"));

			Map<String, String> localeMessages = new HashMap<String, String>();
			Enumeration<Object> keys = bundle.keys();

			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				String value = bundle.getProperty(key.toString());
				localeMessages.put(key.toString(), value);
			}
			messages.put(locale.getLanguage(), localeMessages);
		}

		if (messages.get(defaultLocale.getLanguage()) == null) {
			throw new RuntimeException(
					"Default locale messages bundle was not found.");
		}
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public Locale getLocaleByLanguage(String langCode) {
		for (Locale locale : supportedLocales) {
			if (locale.getLanguage().equals(langCode)) {
				return locale;
			}
		}
		return null;
	}
}
