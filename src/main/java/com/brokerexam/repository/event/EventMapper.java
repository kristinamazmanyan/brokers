package com.brokerexam.repository.event;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.brokerexam.domain.event.Event;
import com.brokerexam.repository.DBUtils;
import org.springframework.jdbc.core.RowMapper;

public class EventMapper implements RowMapper {
	public static final String EVENT_TYPE = "event_type";
	public static final String MESSAGE = "message";
	public static final String PERFORMED_BY = "performed_by";
	public static final String PERFORMED_ON = "performed_on";
	public static final String CREATED_AT = "created_at";
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setId(rs.getLong(DBUtils.id));
		return event;
	}

}
