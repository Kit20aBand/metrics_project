package com.metrics.view.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageSender {

	private static String DEFAULT_DETAIL_SUFFIX = ".Detail";

	private MessageSender() {
	}

	public static void sendMessage(final String messageId,
			final Severity severity,
			final Object... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				getMessage(messageId, severity, params));
	}

	public static FacesMessage getMessage(final String messageId,
			final Severity severity, final Object... params) {
		String summary = null;
		String detail = null;
		final FacesContext context = FacesContext.getCurrentInstance();
		final ResourceBundle bundle = context.getApplication()
				.getResourceBundle(context, "i18n");

		try {
			summary = getFormattedText(getLocale(),
					bundle.getString(messageId), params);
		} catch (final MissingResourceException e) {
			summary = messageId;
		}

		try {
			detail = getFormattedText(getLocale(),
					bundle.getString(messageId + DEFAULT_DETAIL_SUFFIX), params);
		} catch (final MissingResourceException e) {

		}

		return new FacesMessage(severity, summary, detail);
	}

	private static String getFormattedText(final Locale locale,
			final String message, final Object params[]) {
		MessageFormat messageFormat = null;

		if (params == null || message == null) {
			return message;
		}

		messageFormat = locale == null ? new MessageFormat(message)
		: new MessageFormat(message, locale);
		return messageFormat.format(params);
	}

	private static Locale getLocale() {
		Locale locale = null;
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && facesContext.getViewRoot() != null) {
			locale = facesContext.getViewRoot().getLocale();
			if (locale == null) {
				locale = Locale.getDefault();
			}
		} else {
			locale = Locale.getDefault();
		}

		return locale;
	}
}
