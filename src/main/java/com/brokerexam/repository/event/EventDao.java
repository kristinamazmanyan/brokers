package com.brokerexam.repository.event;

import com.brokerexam.bean.event.EventListBean;
import com.brokerexam.bean.event.SearchEventBean;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.event.Event;


public interface EventDao {
	void saveEvent(Event event);
	PagingResult<EventListBean> search(SearchEventBean eventBean, int startRowIndex, int rowCount,
			String sortBy, boolean ascending);
	
}
