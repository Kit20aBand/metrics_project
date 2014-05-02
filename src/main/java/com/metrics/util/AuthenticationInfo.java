package com.metrics.util;

import static com.metrics.util.AuthenticationFacade.getAuthentication;

import javax.inject.Inject;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Component;

import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IUserService;

@Component
public class AuthenticationInfo {

	@Inject
	private IUserService service;

	private User user;

	public User findUser() {
		if (isAuthentication()) {
			final String username = getAuthenticationName();
			user = service.getUser(username);
			return user;
		}
		return null;
	}

	public boolean isAuthentication() {
		if (getAuthentication() instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return true;
	}

	public String getAuthenticationName() {
		return getAuthentication().getName();
	}

}