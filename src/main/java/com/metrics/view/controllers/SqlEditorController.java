package com.metrics.view.controllers;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.dao.common.ISqlEditor;

@Named
@Scope("request")
public class SqlEditorController {

	private static final Log log = LogFactory.getLog(SqlEditorController.class);

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
			log.info(commandToExecute);
			if (command.equalsIgnoreCase("select")) {
				Arrays.toString(sqlEditor.findWithSql(commandToExecute));
				// answerBody =
				// sqlEditor.findWithSql(commandToExecute).toString();
				return answerBody;
			} else {
				answerBody = "You can not perform select commands";
				return answerBody;
			}
		} catch (final Exception e) {
			answerBody = e.getMessage();
			return answerBody;
		}
	}
}
