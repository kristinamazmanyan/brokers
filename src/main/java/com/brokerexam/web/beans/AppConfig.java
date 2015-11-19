package com.brokerexam.web.beans;

import org.springframework.stereotype.Component;

@Component("appConfig")
public class AppConfig {
	
	private static String appContext ;

	public String getAppContext() {
		return appContext;
	}

	public static void setAppContext(String appContext) {
		AppConfig.appContext = appContext;
	}
	
	public String getResources() {
		return appContext + "/static" ;
	}
}
