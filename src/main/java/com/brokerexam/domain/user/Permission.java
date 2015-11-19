package com.brokerexam.domain.user;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = -3839014953101709546L;
	private long id;
	private String name;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Permission == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Permission other = (Permission) obj;
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
