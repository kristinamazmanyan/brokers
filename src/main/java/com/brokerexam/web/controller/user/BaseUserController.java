package com.brokerexam.web.controller.user;

import com.brokerexam.service.user.RoleService;
import com.brokerexam.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;

public class BaseUserController implements Serializable {
	private static final long serialVersionUID = -7558078743094131048L;

	protected int dataCount = 0;

	@Autowired
	@Qualifier("userService")
	protected transient UserService userService;

	@Autowired
	@Qualifier("roleService")
	protected transient RoleService roleService;

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
}
