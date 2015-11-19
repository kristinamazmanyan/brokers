package com.brokerexam.common.bean;

import java.io.Serializable;

public class PageBean implements Serializable {
	private static final long serialVersionUID = 4884027634516869649L;

	private int pageNumber;
	private String cssClassName;
	private boolean pageDisabled;

	public PageBean(int pageNumber, String cssClassName, boolean pageDisabled) {
		this.pageNumber = pageNumber;
		this.cssClassName = cssClassName;
		this.pageDisabled = pageDisabled;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getCssClassName() {
		return cssClassName;
	}

	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

	public boolean isPageDisabled() {
		return pageDisabled;
	}

	public void setPageDisabled(boolean pageDisabled) {
		this.pageDisabled = pageDisabled;
	}
}
