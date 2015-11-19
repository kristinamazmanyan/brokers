package com.brokerexam.web.controller.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.brokerexam.service.event.EventService;
import com.brokerexam.service.user.UserService;
import com.brokerexam.web.auth.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.brokerexam.bean.event.SearchEventBean;
import com.brokerexam.common.security.UserDetails;
import com.brokerexam.domain.Base;

@Controller("eventListController")
@Scope("view")
public class EventListController implements Serializable {

	private static final long serialVersionUID = -8079570566706544953L;

	protected static final int DEFAULT_PAGE_SIZE = 10;
	private int pageSize = DEFAULT_PAGE_SIZE;

	private SearchEventBean searchEventBean;
	private List<SelectItem> userSelectItems;

	@Autowired
	private transient UserDetails userDetails;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("eventService")
	private EventService eventService;

	private EventsLazyDataModel eventsLazyDataModel;

	@PostConstruct
	public void init() {
		userDetails.check(Permissions.VIEW_EVENTS);

		List<Base> users = userService.lookupUsers();
		userSelectItems = new ArrayList<SelectItem>();

		for (Base user : users) {
			userSelectItems.add(new SelectItem(user.getId(), user.getName()));
		}

		searchEventBean = new SearchEventBean();

		eventsLazyDataModel = new EventsLazyDataModel();
		eventsLazyDataModel.setSearchBean(searchEventBean);
		eventsLazyDataModel.setPageSize(pageSize);
		eventsLazyDataModel.setEventService(eventService);
	}

	public void onSearch(ActionEvent event) {
		eventsLazyDataModel.setPageSize(pageSize);
	}

	public void onClearSearch(ActionEvent event) {
		searchEventBean.reset();
		eventsLazyDataModel.setPageSize(pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public SearchEventBean getSearchEventBean() {
		return searchEventBean;
	}

	public void setSearchEventBean(SearchEventBean searchEventBean) {
		this.searchEventBean = searchEventBean;
	}

	public List<SelectItem> getUserSelectItems() {
		return userSelectItems;
	}

	public void setUserSelectItems(List<SelectItem> userSelectItems) {
		this.userSelectItems = userSelectItems;
	}

	public EventsLazyDataModel getEventsLazyDataModel() {
		return eventsLazyDataModel;
	}

	public void setEventsLazyDataModel(EventsLazyDataModel eventsLazyDataModel) {
		this.eventsLazyDataModel = eventsLazyDataModel;
	}

}
