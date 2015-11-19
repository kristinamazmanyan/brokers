package com.brokerexam.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {
	private static final long serialVersionUID = 2819815596158221410L;

	private boolean success = true;
	private List<String> messages = new ArrayList<String>();
	private List<String> messageKeys = new ArrayList<String>();
	private String description = "";

	public void addMsgKey(String key) {
		messageKeys.add(key);
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return messages.isEmpty() ? "" : messages.get(0);
	}

	public String getMsgKey() {
		return messageKeys.isEmpty() ? "" : messageKeys.get(0);
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
