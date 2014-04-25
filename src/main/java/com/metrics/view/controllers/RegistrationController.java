package com.metrics.view.controllers;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IUserService;
import com.metrics.view.util.CommonPages;

@Named
@Scope("request")
public class RegistrationController {
	private static final Log log = LogFactory
			.getLog(RegistrationController.class);

	@Inject
	private IUserService service;

	private User user = new User();

	{
		final Role role = new Role(Role.ROLE_USER);
		// final Role role = new Role(Role.ROLE_ADMIN);
		user.setRole(role);
	}

	public String doRegister() {
		log.info("Registering...");
		service.create(user);
		log.info("Registration success");
		return CommonPages.loginPage
				+ "?faces-redirect=true&afterRegistration=true";
	}

	public User getUser() {
		return user;
	}

}
