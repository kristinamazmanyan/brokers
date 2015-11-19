package com.brokerexam.web.component;

import java.util.Map;

import javax.faces.component.UIComponentBase;

import com.brokerexam.common.ex.AccessDeniedException;
import com.brokerexam.common.security.UserDetails;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.web.util.JsfUtils;

public class AuthCheck extends UIComponentBase {

	private final static String ATTR_HAS_PERMISSION = "hasPermission";
	private final static String ATTR_CHECK_PERMISSION = "checkPermission";

	public AuthCheck() {
		super();
	}

	@Override
	public String getFamily() {
		return "dbt.AuthCheck";
	}

	@Override
	public boolean isRendered() {
		UserDetails userDetails = JsfUtils.getUser();

		if (userDetails == null) {
			throw new AccessDeniedException();
		}

		final Map<String, Object> attributes = getAttributes();
		String checkPermission = (String) attributes.get(ATTR_CHECK_PERMISSION);

		if (TextUtils.hasText(checkPermission)) {
			if (!userDetails.hasPermission(checkPermission)) {
				throw new AccessDeniedException();
			}
		}

		String hasPermission = (String) attributes.get(ATTR_HAS_PERMISSION);

		return userDetails.hasPermission(hasPermission);
	}

}
