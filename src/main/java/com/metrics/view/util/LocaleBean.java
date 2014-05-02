package com.metrics.view.util;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class LocaleBean implements Serializable {
	private static final Log log = LogFactory.getLog(LocaleBean.class);

	private Locale locale = new Locale("en");

	public void setLanguage(final String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
	}

}