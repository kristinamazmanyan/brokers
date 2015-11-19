package com.brokerexam.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.brokerexam.common.util.Constants;

public class PagingBean<T> implements Serializable {
	private static final long serialVersionUID = 6749950131886717935L;
	private int pageCount = 1;
	private int visiblePageCount = Constants.PAGING_VISIBLE_PAGE_COUNT;
	private int rowCount = Constants.PAGE_SIZE;
	private int currentPage = 1;
	private int dataCount;
	private boolean previousPageEnabled;
	private boolean firstPageEnabled;
	private boolean nextPageEnabled;

	private boolean lastPageEnabled;

	private List<PageBean> pageBeanList = new ArrayList<PageBean>();

	private T searchBean;

	public void initControlsRenderingCases() {
		if (pageCount > 1) {

			boolean firstLastPageEnabled = (pageCount > 2);

			if (currentPage == 1) {
				previousPageEnabled = false;
				firstPageEnabled = false;
				nextPageEnabled = true;
				lastPageEnabled = firstLastPageEnabled;
			} else if (currentPage == pageCount) {
				previousPageEnabled = true;
				firstPageEnabled = firstLastPageEnabled;
				nextPageEnabled = false;
				lastPageEnabled = false;
			} else {
				previousPageEnabled = true;
				firstPageEnabled = firstLastPageEnabled && (currentPage > 2);
				nextPageEnabled = true;
				lastPageEnabled = firstLastPageEnabled
						&& (currentPage < (pageCount - 1));
			}
		}
	}

	public void initPageCountByDataCount(int count) {
		dataCount = count;

		if (count <= rowCount) {
			pageCount = 1;

			return;
		}

		pageCount = count / rowCount;
		if ((count % rowCount) > 0) {
			pageCount += 1;
		}
	}

	public void initPageBeanList() {
		int startIndex = 1;
		int endIndex = startIndex + visiblePageCount;

		if (pageCount > visiblePageCount) {
			if ((currentPage / visiblePageCount) > 0) {
				startIndex *= (visiblePageCount * (currentPage / visiblePageCount));

				endIndex = startIndex + visiblePageCount;
				if (endIndex > pageCount) {
					endIndex = pageCount;
					startIndex = pageCount - visiblePageCount;
				}
			}

			if (currentPage == endIndex) {
				if (currentPage < pageCount) {
					if ((pageCount - currentPage) >= Constants.PAGE_AFTER_SHOW) {
						startIndex += Constants.PAGE_AFTER_SHOW;
						endIndex += Constants.PAGE_AFTER_SHOW;
					} else {
						startIndex += 1;
						endIndex += 1;
					}
				}
			} else if (currentPage == startIndex) {
				if (currentPage > 1) {
					if ((currentPage - 1) >= Constants.PAGE_AFTER_SHOW) {
						startIndex -= Constants.PAGE_AFTER_SHOW;
						endIndex -= Constants.PAGE_AFTER_SHOW;
					} else {
						startIndex -= 1;
						endIndex -= 1;
					}
				}
			}
		} else {
			endIndex = pageCount;
		}

		pageBeanList = new ArrayList<PageBean>();

		for (int i = startIndex; i <= endIndex; i++) {
			if (i == currentPage) {
				pageBeanList.add(new PageBean(i,
						Constants.PAGING_SELECTED_PAGE_STYLE, true));
			} else {
				pageBeanList.add(new PageBean(i,
						Constants.PAGING_NO_SELECTED_PAGE_STYLE, false));
			}
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getVisiblePageCount() {
		return visiblePageCount;
	}

	public void setVisiblePageCount(int visiblePageCount) {
		this.visiblePageCount = visiblePageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public boolean isPreviousPageEnabled() {
		return previousPageEnabled;
	}

	public void setPreviousPageEnabled(boolean previousPageEnabled) {
		this.previousPageEnabled = previousPageEnabled;
	}

	public boolean isFirstPageEnabled() {
		return firstPageEnabled;
	}

	public void setFirstPageEnabled(boolean firstPageEnabled) {
		this.firstPageEnabled = firstPageEnabled;
	}

	public boolean isNextPageEnabled() {
		return nextPageEnabled;
	}

	public void setNextPageEnabled(boolean nextPageEnabled) {
		this.nextPageEnabled = nextPageEnabled;
	}

	public boolean isLastPageEnabled() {
		return lastPageEnabled;
	}

	public void setLastPageEnabled(boolean lastPageEnabled) {
		this.lastPageEnabled = lastPageEnabled;
	}

	public List<PageBean> getPageBeanList() {
		return pageBeanList;
	}

	public void setPageBeanList(List<PageBean> pageBeanList) {
		this.pageBeanList = pageBeanList;
	}

	public T getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(T searchBean) {
		this.searchBean = searchBean;
	}

}
