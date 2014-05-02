package com.metrics.view.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;
import com.metrics.util.ThingsOverWhichIsWorking;
import com.metrics.view.datamodel.GenericDataModel;

@Named
@Scope("request")
public class AllEventsController {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	@Qualifier("eventService")
	private ICommonOperations<Event> service;

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	private GenericDataModel<Event> dataModel;

	@PostConstruct
	public void init() {
		final Project project = thingsOverWhichIsWorking.getActiveProject();
		dataModel = new GenericDataModel<Event>(service);
		dataModel.setNamedQuery(Event.FIND_BY_PROJECT);
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("project", project);
		dataModel.setParams(params);
	}

	public GenericDataModel<Event> getDataModel() {
		return dataModel;
	}


}
