package com.metrics.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletUtil {

	public static ServletRequest getRequest() {
		final ServletRequest request = (ServletRequest) getExternalContext()
				.getRequest();
		return request;
	}

	public static ServletResponse getResponce() {
		final ServletResponse responce = (ServletResponse) getExternalContext()
				.getResponse();
		return responce;
	}

	public static RequestDispatcher getRequestDispatcher(final String resource) {
		final RequestDispatcher dispatcher = ((ServletRequest) getExternalContext()
				.getRequest()).getRequestDispatcher(resource);
		return dispatcher;
	}

	public static void responseComplete() {
		FacesContext.getCurrentInstance().responseComplete();
	}

	private static ExternalContext getExternalContext() {
		final ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		return context;
	}
}
