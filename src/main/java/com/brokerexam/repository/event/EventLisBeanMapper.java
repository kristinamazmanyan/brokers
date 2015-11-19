package com.brokerexam.repository.event;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.brokerexam.bean.event.EventListBean;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.repository.DBUtils;
import org.springframework.jdbc.core.RowMapper;

public class EventLisBeanMapper implements RowMapper<EventListBean> {
	
	@Override
	public EventListBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventListBean event = new EventListBean();
		event.setId(rs.getLong(DBUtils.id));
		int eventTypeValue = rs.getInt(EventMapper.EVENT_TYPE);
		EventType eventType = EventType.getByValue(eventTypeValue);
		
		if (eventType != null) {
			event.setEventType(eventType.getOperation());
		}
		
		event.setMessage(rs.getString(EventMapper.MESSAGE));
		event.setPerformedBy(rs.getString(EventMapper.PERFORMED_BY));
		event.setPerformedOn(rs.getString(EventMapper.PERFORMED_ON));
		event.setCreatedAt(rs.getTimestamp(EventMapper.CREATED_AT));
		return event;
	}

}
