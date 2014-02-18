package com.metrics.view.controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IUserService;
import com.metrics.view.datamodel.UserDataModel;

@Named
@RequestScoped
public class AllUsersController {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	private IUserService service;

	private ListDataModel<User> dataModel;

	public ListDataModel<User> getDataModel() {
		dataModel = new UserDataModel(service.findAll());
		return dataModel;
	}

}
