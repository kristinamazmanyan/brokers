package com.brokerexam.service.event;

import com.brokerexam.domain.event.Event;
import com.brokerexam.bean.event.EventListBean;
import com.brokerexam.bean.event.SearchEventBean;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.event.EventType;

public interface EventService {
	void saveEvent(Event event);

	void saveEvent(EventType eventType, String message,
			long performedBy, String performedOn);
	
	PagingResult<EventListBean> search(SearchEventBean eventBean, int startRowIndex, int rowCount,
			String sortBy, boolean ascending);
}
