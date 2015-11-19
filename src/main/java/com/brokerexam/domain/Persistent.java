package com.brokerexam.domain;

import java.io.Serializable;
import java.util.Date;

public class Persistent implements Serializable {
	private static final long serialVersionUID = 5677284540545557741L;

	protected long id = 0;
	protected long createdBy = 0;
	protected Date createdAt = null;
	protected long changedBy = 0;
	protected Date changedAt = null;

	public Persistent() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(long changedBy) {
		this.changedBy = changedBy;
	}

	public Date getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
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
		if (obj instanceof Persistent == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		final Persistent other = (Persistent) obj;
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
