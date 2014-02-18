package com.metrics.view.controllers;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import com.metrics.security.IAuthenticationFacade;
import com.metrics.view.util.MessageSender;

@Named
@RequestScoped
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);

	@Inject
	private IAuthenticationFacade authenticationFacade;

	private String username;

	private String password;

	public void doLogin() throws ServletException, IOException {
		final ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		final RequestDispatcher dispatcher = ((ServletRequest) context
				.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(),
				(ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void checkSuccessSignIn() {
		final ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		final ServletRequest request = (ServletRequest) context.getRequest();
		if (Boolean.parseBoolean(request.getParameter("error"))) {
			MessageSender
			.sendMessage("login.Fail", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void logout() throws ServletException, IOException {
		final ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		final RequestDispatcher dispatcher = ((ServletRequest) context
				.getRequest())
				.getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward((ServletRequest) context.getRequest(),
				(ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
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
		FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if (isAuthentication()) {
			return "Hello, "
					+ authenticationFacade.getAuthentication().getName();
		}
		return "Hello, guest";
	}

	public boolean isAuthentication() {
		if (authenticationFacade.getAuthentication() instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return true;
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
