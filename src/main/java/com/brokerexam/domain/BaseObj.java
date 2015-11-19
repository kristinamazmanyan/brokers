package com.brokerexam.domain;

import java.io.Serializable;

public class BaseObj implements Serializable, Base {
	private static final long serialVersionUID = 8183705471445682203L;
	private long id = 0;
	private String name;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (obj instanceof BaseObj == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final BaseObj other = (BaseObj) obj;
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
