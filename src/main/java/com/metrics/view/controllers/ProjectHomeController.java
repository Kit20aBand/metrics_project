package com.metrics.view.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Event;
import com.metrics.view.datamodel.GenericDataModel;

@Named
@Scope("request")
public class ProjectHomeController {

	@Inject
	@Qualifier("eventService")
	private ICommonOperations<Event> service;

	private GenericDataModel<Event> dataModel;

	@PostConstruct
	public void init() {
		dataModel = new GenericDataModel<Event>(service);

	}

	public GenericDataModel<Event> getDataModel() {
		return dataModel;
	}

}
