package com.brokerexam.service.event;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import com.brokerexam.common.security.UserDetails;
import com.brokerexam.domain.event.EventType;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class EventLog implements Serializable {
	private static final long serialVersionUID = 920174695714276211L;

	private static EventLog log = null;
	private EventService eventService;

	private EventLog() {
		ApplicationContext ctx = FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance());
		eventService = (EventService) ctx.getBean("eventService");
	}

	public static EventLog getInstance() {
		if (log == null) {
			return new EventLog();
		}
		return log;
	}

	public void write(EventType eventType, String message, long performedBy,
			String performedOn) {
		eventService.saveEvent(eventType, message, performedBy, performedOn);
	}

	public void write(EventType eventType, String message,
			UserDetails userDetails, String performedOn) {
		eventService.saveEvent(eventType, message, userDetails.getId(),
				performedOn);
	}

	public void write(EventType eventType, String message,
			UserDetails userDetails, long performedOn) {
		eventService.saveEvent(eventType, message, userDetails.getId(),
				performedOn + "");
	}

	public void write(EventType eventType, long performedBy, long performedOn) {
		eventService.saveEvent(eventType, "", performedBy, performedOn + "");
	}

	public void write(EventType eventType, String message, long performedBy,
			long performedOn) {
		eventService.saveEvent(eventType, message, performedBy, performedOn
				+ "");
	}

	public void write(EventType eventType, long performedBy, String performedOn) {
		eventService.saveEvent(eventType, "", performedBy, performedOn);
	}

	public void write(EventType eventType, UserDetails userDetails,
			String performedOn) {
		eventService.saveEvent(eventType, "", userDetails.getId(), performedOn);
	}

	public void write(EventType eventType, UserDetails userDetails,
			long performedOn) {
		eventService.saveEvent(eventType, "", userDetails.getId(), performedOn
				+ "");
	}
}
