package com.brokerexam.web.scope;


import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.FacesRequestAttributes;

@Component("viewScopeBean")
public class SpringViewScope implements Scope {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#get(java.lang.String, org.springframework.beans.factory.ObjectFactory)
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			Map<String, Object> viewMap = FacesContext.getCurrentInstance()
					.getViewRoot().getViewMap();
			if (viewMap.containsKey(name)) {
				return viewMap.get(name);
			} else {
				Object object = objectFactory.getObject();
				viewMap.put(name, object);
				return object;
			}
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#remove(java.lang.String)
	 */
	@Override
	public Object remove(String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.remove(name);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#registerDestructionCallback(java.lang.String, java.lang.Runnable)
	 */
	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// not supported
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#getConversationId()
	 */
	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(
				facesContext);
		return facesRequestAttributes.getSessionId() + "-"
				+ facesContext.getViewRoot().getViewId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.Scope#resolveContextualObject(java.lang.String)
	 */
	@Override
	public Object resolveContextualObject(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(
				facesContext);
		return facesRequestAttributes.resolveReference(key);
	}

}
