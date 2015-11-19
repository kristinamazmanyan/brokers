package com.brokerexam.web.controller.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.brokerexam.bean.event.EventListBean;
import com.brokerexam.bean.event.SearchEventBean;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.service.event.EventService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventsLazyDataModel extends LazyDataModel<EventListBean> implements
		Serializable {

	private static final long serialVersionUID = -1261017477406056272L;

	private static final Logger LOG = LoggerFactory
			.getLogger(EventsLazyDataModel.class);

	private long total = 0;
	private SearchEventBean searchBean;
	private List<EventListBean> beans = new ArrayList<EventListBean>();
	private EventService eventService = null;

	public EventsLazyDataModel() {
		super();
	}

	public List<EventListBean> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {
			beans.clear();

			PagingResult<EventListBean> pagingResult = eventService.search(
					searchBean, first, pageSize, sortField,
					SortOrder.ASCENDING.equals(sortOrder));

			total = pagingResult.getTotalRows();

			beans = pagingResult.getList();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return beans;
	}

	public int getRowCount() {
		return (int) total;
	}

	@Override
	public EventListBean getRowData() {
		if (isRowAvailable()) {
			return super.getRowData();
		} else {
			return null;
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public SearchEventBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(SearchEventBean searchBean) {
		this.searchBean = searchBean;
	}

	public List<EventListBean> getBeans() {
		return beans;
	}

	public void setBeans(List<EventListBean> beans) {
		this.beans = beans;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

}
