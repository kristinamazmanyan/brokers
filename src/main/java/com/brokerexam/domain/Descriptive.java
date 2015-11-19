package com.brokerexam.domain;

import java.io.Serializable;

public class Descriptive extends Persistent implements Base, Serializable {
	private static final long serialVersionUID = 2705981417965124682L;
	
	protected String name;
	protected String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
