package com.metrics.view.controllers;

import static com.metrics.util.ServletUtil.getRequest;
import static com.metrics.util.ServletUtil.getRequestDispatcher;
import static com.metrics.util.ServletUtil.getResponce;
import static com.metrics.util.ServletUtil.responseComplete;
import static com.metrics.view.util.MessageUtil.findMessageFromBundle;
import static com.metrics.view.util.MessageUtil.getFormattedText;
import static com.metrics.view.util.MessageUtil.sendMessage;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Role;
import com.metrics.util.AuthenticationInfo;

@Named
@Scope("request")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);

	@Inject
	private AuthenticationInfo authenticationInfo;

	private String username;

	private String password;

	public void doLogin() throws ServletException, IOException {
		final RequestDispatcher dispatcher = getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(getRequest(), getResponce());
		responseComplete();
	}

	public void checkSuccessSignIn() {
		final ServletRequest request = getRequest();
		if (Boolean.parseBoolean(request.getParameter("error"))) {
			sendMessage("login.Fail", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void checkLoginAfterRegistration() {
		final ServletRequest request = getRequest();
		if (Boolean.parseBoolean(request.getParameter("afterRegistration"))) {
			sendMessage("login.AfterRegistration", FacesMessage.SEVERITY_INFO);
		}
	}

	public void logout() throws ServletException, IOException {
		final RequestDispatcher dispatcher = getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward(getRequest(), getResponce());
		responseComplete();
	}

	public boolean renderSignInButton() {
		if (isAuthentication()) {
			return false;
		}
		return true;
	}

	public boolean renderLogoutButton() {
		return !renderSignInButton();
	}

	public String getAuthenticationName() {
		if (isAuthentication()) {
			final Object[] params = { authenticationInfo
					.getAuthenticationName() };
			return getFormattedText("login.HelloUser", params);
		} else {
			final Object[] params = { findMessageFromBundle("login.GuestName") };
			return getFormattedText("login.HelloUser", params);
		}
	}

	public boolean isAuthentication() {
		return authenticationInfo.isAuthentication();
	}

	public boolean isUser() {
		if (authenticationInfo.findUser() == null) {
			return false;
		}
		return authenticationInfo.findUser().getRole().getRole()
				.equals(Role.ROLE_USER);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
