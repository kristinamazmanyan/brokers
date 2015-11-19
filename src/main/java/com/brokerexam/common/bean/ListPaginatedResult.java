package com.brokerexam.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListPaginatedResult<T> implements Serializable {
	
	private static final long serialVersionUID = -2458179267161713858L;
	
	private long total = 0;
	private List<T> result = new ArrayList<T>();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
}
