package com.brokerexam.common.bean;

import java.io.Serializable;
import java.util.List;

public class PagingResult<T> implements Serializable {
	private static final long serialVersionUID = -5231093054814800826L;

	private List<T> list;
	private long startRow;
	private long endRow;
	private long totalRows;

	public PagingResult() {
		list = null;
		startRow = 0;
		endRow = 0;
		totalRows = 0;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getEndRow() {
		return endRow;
	}

	public void setEndRow(long endRow) {
		this.endRow = endRow;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

}
