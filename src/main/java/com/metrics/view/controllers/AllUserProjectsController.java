package com.metrics.view.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Project;
import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IProjectService;
import com.metrics.util.AuthenticationInfo;
import com.metrics.util.ThingsOverWhichIsWorking;
import com.metrics.view.util.CommonPages;

@Named
@Scope("request")
public class AllUserProjectsController {

	private static final Log log = LogFactory
			.getLog(AllUserProjectsController.class);

	@Inject
	private IProjectService projectService;

	@Inject
	private AuthenticationInfo authenticationInfo;

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	private List<Project> projects;

	private Project selectedProject;

	private List<Project> filteredProjects;

	@PostConstruct
	public void init() {
		final User user = authenticationInfo.findUser();
		projects = projectService.getProjects(user);

	}

	public String goToProject() {
		thingsOverWhichIsWorking.setActiveProject(selectedProject);
		System.out.println(selectedProject);
		return CommonPages.USER_PROJECT_PAGE + "?faces-redirect=true";
	}

	public void deleteProject() {
		log.info("Selected project " + selectedProject);
		System.out.println(selectedProject);
		projectService.delete(selectedProject);
		projects.remove(selectedProject);
	}

	public List<Project> getFilteredProjects() {
		return filteredProjects;
	}

	public void setFilteredProjects(final List<Project> filteredProjects) {
		this.filteredProjects = filteredProjects;
	}

	public void setSelectedProject(final Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}
}
