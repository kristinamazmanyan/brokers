package com.brokerexam.domain.event;


public enum EventType {
	
	CREATE_ROLE(1, "Create role"), UPDATE_ROLE(2, "Update role"), DELETE_ROLE(3, "Delete role"),
	CREATE_USER(4, "Create user"), UPDATE_USER(5, "Update user"), DELETE_USER(6, "Delete user"),
	CHANGE_PASSWORD(7, "Change password"),
	CREATE_TEST(8, "Create exam"), UPDATE_TEST(9, "Update exam"), DELETE_TEST(10, "Delete exam"),ARCHIVE_TEST(11, "Archive exam"),
	CREATE_MEMBER(12, "Create exam member"), UPDATE_MEMBER(13, "Update exam member"), DELETE_MEMBER(14, "Delete exam member"),ACTIVE_MEMBER(15, "Active exam member"),
	CREATE_CM(16, "Create committee member"), UPDATE_CM(17, "Update committee member"), DELETE_CM(18, "Delete committee member"),
	UPLOAD_QUESTIONS(19, "Upload questions");

	private int value;
	private String operation;
	
	private EventType(int value, String operation) {
		this.value = value;
		this.operation = operation;
	}
	
	public static EventType getByValue(int value) {
		EventType[] values = values();
		
		for (EventType eventType : values) {
			if (eventType.getValue() == value) {
				return eventType;
			}
		}
		return null;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
