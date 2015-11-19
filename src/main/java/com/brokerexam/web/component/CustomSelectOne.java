package com.brokerexam.web.component;

import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;


public class CustomSelectOne extends UISelectOne {

    public CustomSelectOne() {
        super();        
    }

    /**
     * Skip the validation.
     */    
    @Override
    protected void validateValue(FacesContext context, Object value) {        
    }
}