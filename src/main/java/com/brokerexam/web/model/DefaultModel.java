package com.brokerexam.web.model;

import java.util.HashMap;

import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

public class DefaultModel extends ModelAndView {
	
	public DefaultModel(String viewName){
		super(viewName, new HashMap<String, Object>()) ;
	}
	
	@SuppressWarnings("unchecked")
	public DefaultModel(String viewName, Object... modelData){
		super(viewName, new HashMap<String, Object>()) ;
		Assert.isTrue(modelData.length%2 == 0, "Model data length should be even") ;
		for (int i = 0; i < modelData.length/2; i++) {
			getModel().put(modelData[2*i].toString(), modelData[2*i + 1]) ;
		}
	}
	
}
