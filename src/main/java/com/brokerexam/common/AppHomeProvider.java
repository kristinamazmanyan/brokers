package com.brokerexam.common;

public class AppHomeProvider {

	private String appHome;

	public void init() {
		appHome = System.getenv("com_brokerexam_HOME");
		
		if (appHome == null) {
			appHome = System.getProperty("com_brokerexam_HOME");
		}
		if (appHome == null) {
			appHome = "D:\\work\\proc_exam_workspace\\brokerexam\\support\\HOME";
		}
	}

	public String getAppHome() {
		return appHome;
	}

	public void setAppHome(String appHome) {
		this.appHome = appHome;
	}

}
