package com.brokerexam.domain.event;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

	private static final long serialVersionUID = -2460042051950314028L;

	private long id;
	private int eventType;
	private String message;
	private long performedBy;
	private String performedOn;
	private Date createdAt = null;

	public Event() {
		super();
	}

	public Event(EventType eventType, String message, long performedBy,
			String performedOn) {
		super();
		this.eventType = eventType.getValue();
		this.message = message;
		this.performedBy = performedBy;
		this.performedOn = performedOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(long performedBy) {
		this.performedBy = performedBy;
	}

	public String getPerformedOn() {
		return performedOn;
	}

	public void setPerformedOn(String performedOn) {
		this.performedOn = performedOn;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		if (id == 0) {
			return System.identityHashCode(this);
		}

		return (int) ((id >> 32) ^ id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Event == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Event other = (Event) obj;
		final long otherId = other.getId();

		if (id == 0) {
			if (otherId != 0) {
				return false;
			}
		} else if (!(id == otherId)) {
			return false;
		}
		return true;
	}
}
