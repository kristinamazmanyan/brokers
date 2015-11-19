package com.brokerexam.bean.event;

import java.io.Serializable;
import java.util.Date;

public class SearchEventBean implements Serializable {
	private static final long serialVersionUID = -336225589246099954L;

	private boolean ascending = false;
	private int sortType = -1;
	private int sortBy;
	private int currentPage = 1;

	private long performedBy;
	private Date createdAtStart = null;
	private Date createdAtEnd = null;

	public SearchEventBean(SearchEventBean bean) {
		super();
		this.performedBy = bean.getPerformedBy();
		this.createdAtStart = bean.getCreatedAtStart();
		this.createdAtEnd = bean.getCreatedAtEnd();
	}

	public SearchEventBean() {
		super();
	}

	public void reset() {
		performedBy = 0;
		createdAtStart = null;
		createdAtEnd = null;

		ascending = false;
		sortType = -1;
		sortBy = 0;
		currentPage = 1;
	}

	public long getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(long performedBy) {
		this.performedBy = performedBy;
	}

	public Date getCreatedAtStart() {
		return createdAtStart;
	}

	public void setCreatedAtStart(Date createdAtStart) {
		this.createdAtStart = createdAtStart;
	}

	public Date getCreatedAtEnd() {
		return createdAtEnd;
	}

	public void setCreatedAtEnd(Date createdAtEnd) {
		this.createdAtEnd = createdAtEnd;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
