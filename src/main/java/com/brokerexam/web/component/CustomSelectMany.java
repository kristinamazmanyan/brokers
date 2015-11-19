package com.brokerexam.web.component;

import javax.faces.component.html.HtmlSelectManyListbox;
import javax.faces.context.FacesContext;


public class CustomSelectMany extends HtmlSelectManyListbox {

    public CustomSelectMany() {
        super();        
    }

    /**
     * Skip the validation.
     */    
    @Override
    protected void validateValue(FacesContext context, Object value) {        
    }
    
//    /**
//     * Skip the validation.
//     */ 
//	@Override
//	public void validate(FacesContext context) {
//	}
//    
    
}