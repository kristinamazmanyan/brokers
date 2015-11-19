package com.brokerexam.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringAppContextProvider implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
	}
	
	public static final ApplicationContext getContext() {
		return context;
	}
}
