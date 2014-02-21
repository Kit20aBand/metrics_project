package com.metrics.view.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Project;
import com.metrics.persistence.service.IProjectService;
import com.metrics.security.util.AuthenticationInfo;
import com.metrics.view.util.CommonPages;

@Named
@Scope("request")
public class CreateProjectController {
	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	private IProjectService service;

	@Inject
	private AuthenticationInfo authenticationInfo;

	private Project project = new Project();

	@PostConstruct
	void init() {
		log.info("1111111111111111111111");
		project.setUser(authenticationInfo.findUser());
	}

	public String createNewProject() {
		service.create(project);
		return CommonPages.userProjectPage + "?faces-redirect=true";
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

}
