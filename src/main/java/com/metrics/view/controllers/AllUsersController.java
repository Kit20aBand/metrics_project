package com.metrics.view.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.User;
import com.metrics.view.datamodel.GenericDataModel;

@Named
@Scope("request")
public class AllUsersController {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	@Qualifier("userService")
	private ICommonOperations<User> service;

	private GenericDataModel<User> dataModel;



	@PostConstruct
	public void init() {
		dataModel = new GenericDataModel<User>(service);

	}

	public GenericDataModel<User> getDataModel() {
		return dataModel;
	}



}
