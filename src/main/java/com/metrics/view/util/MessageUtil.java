package com.metrics.view.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageUtil {

	private static String DEFAULT_DETAIL_SUFFIX = ".Detail";

	private MessageUtil() {
	}

	public static void sendMessage(final String messageId,
			final Severity severity, final Object... params) {
		FacesContext.getCurrentInstance().addMessage(null,
				getMessage(messageId, severity, params));
	}

	public static FacesMessage getMessage(final String messageId,
			final Severity severity, final Object... params) {
		String summary = null;
		String detail = null;

		try {
			summary = getFormattedText(messageId, params);
		} catch (final MissingResourceException e) {
			summary = messageId;
		}

		try {
			detail = getFormattedText(messageId + DEFAULT_DETAIL_SUFFIX, params);
		} catch (final MissingResourceException e) {

		}

		return new FacesMessage(severity, summary, detail);
	}

	public static String getFormattedText(final String messageId,
			final Object params[]) {
		final String message = findMessageFromBundle(messageId);
		MessageFormat messageFormat = null;

		if (params == null || message == null) {
			return message;
		}

		messageFormat = new MessageFormat(message, getLocale());
		return messageFormat.format(params);
	}

	public static String findMessageFromBundle(final String messageId) {
		final FacesContext context = FacesContext.getCurrentInstance();
		final ResourceBundle bundle = context.getApplication()
				.getResourceBundle(context, "i18n");
		final String message = bundle.getString(messageId);
		return message;
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
