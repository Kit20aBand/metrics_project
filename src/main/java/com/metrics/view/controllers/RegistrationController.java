package com.metrics.view.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IRoleService;
import com.metrics.persistence.service.IUserService;
import com.metrics.view.util.CommonPages;

@Named
@Scope("request")
public class RegistrationController {
	private static final Log log = LogFactory
			.getLog(RegistrationController.class);

	@Inject
	@Qualifier("userService")
	private IUserService service;

	@Inject
	@Qualifier("roleService")
	private IRoleService roleService;

	private User user = new User();

	@PostConstruct
	public void init() {
		final Role role = roleService.getRole(Role.ROLE_USER);
		user.setRole(role);
	}

	public String doRegister() {
		log.info("Registering...");
		service.create(user);
		log.info("Registration success");
		return CommonPages.LOGIN_PAGE
				+ "?faces-redirect=true&afterRegistration=true";
	}

	public User getUser() {
		return user;
	}

}
