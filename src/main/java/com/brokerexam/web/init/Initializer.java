package com.brokerexam.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.brokerexam.web.beans.AppConfig;

/**
 * Application Lifecycle Listener implementation class Initializer
 * 
 */
public class Initializer implements ServletContextListener, HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public Initializer() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent ev) {
		AppConfig.setAppContext(ev.getServletContext().getContextPath());
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}

}
