package com.metrics.view.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Project;
import com.metrics.persistence.service.IProjectService;
import com.metrics.util.AuthenticationInfo;
import com.metrics.util.ThingsOverWhichIsWorking;
import com.metrics.view.util.CommonPages;

@Named
@Scope("request")
public class CreateProjectController {
	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	private IProjectService service;

	@Inject
	private AuthenticationInfo authenticationInfo;

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	private Project project = new Project();

	@PostConstruct
	void init() {
		project.setUser(authenticationInfo.findUser());
		project.setToken(RandomStringUtils.randomAlphabetic(10));
	}

	public String createNewProject() {
		service.create(project);
		thingsOverWhichIsWorking.setActiveProject(project);
		return CommonPages.userProjectPage + "?faces-redirect=true";
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

}
