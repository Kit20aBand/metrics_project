package com.metrics.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class AuthenticationFacade {

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
