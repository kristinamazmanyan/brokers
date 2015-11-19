package com.brokerexam.service.event;

import com.brokerexam.domain.event.Event;
import com.brokerexam.repository.event.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.brokerexam.bean.event.EventListBean;
import com.brokerexam.bean.event.SearchEventBean;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.event.EventType;

@Service("eventService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EventServiceImpl implements EventService {
	
	@Autowired
	@Qualifier("eventDao")
	private EventDao eventDao;

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void saveEvent(Event event) {
		eventDao.saveEvent(event);
	}

	@Override
	public void saveEvent(EventType eventType, String message,
			long performedBy, String performedOn) {
		Event event = new Event();
		event.setEventType(eventType.getValue());
		event.setMessage(message);
		event.setPerformedBy(performedBy);
		event.setPerformedOn(performedOn);
		saveEvent(event);
	}

	@Override
	public PagingResult<EventListBean> search(SearchEventBean eventBean,
			int startRowIndex, int rowCount, String sortBy, boolean ascending) {
		return eventDao.search(eventBean, startRowIndex, rowCount, sortBy, ascending);
	}
	
	
}
