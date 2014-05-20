package com.metrics.view.controllers;

import javax.inject.Inject;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import com.metrics.persistence.dao.common.ISqlEditor;

public class SqlEditorForAdminController {

	@Inject
	private ISqlEditor sqlEditor;

	private String answerBody;

	public String handleCommand(final String command, final String[] params) {
		final SqlExceptionHelper h = new SqlExceptionHelper();
		try {
			String commandToExecute = command;
			for (final String param : params) {
				commandToExecute += " " + param;
			}
			if (command.equalsIgnoreCase("select")) {
				answerBody = sqlEditor.findWithSql(commandToExecute).toString();
				return answerBody;
			} else {
				sqlEditor.runSql(commandToExecute);
				answerBody = "Ok";
				return answerBody;
			}
		} catch (final Exception e) {
			answerBody = e.getMessage();
			return answerBody;
		}
	}
}
