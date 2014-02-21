package com.metrics.view.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Project;
import com.metrics.view.datamodel.GenericDataModel;

@Named
@Scope("request")
public class AllUserProjectsController {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	@Qualifier("projectService")
	private ICommonOperations<Project> service;

	private GenericDataModel<Project> dataModel;

	@PostConstruct
	public void init() {
		dataModel = new GenericDataModel<Project>(service);
	}

	public GenericDataModel<Project> getDataModel() {
		return dataModel;
	}

}
