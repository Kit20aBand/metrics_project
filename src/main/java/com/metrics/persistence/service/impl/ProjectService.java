package com.metrics.persistence.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.metrics.persistence.dao.IProjectDao;
import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IProjectService;
import com.metrics.persistence.service.common.AbstractService;

@Service
public class ProjectService extends AbstractService<Project> implements
IProjectService {

	@Inject
	private IProjectDao dao;

	@Override
	protected ICommonOperations<Project> getDao() {
		return dao;
	}

	@Override
	public List<Project> getProjects(final User user) {
		return dao.getProjects(user);
	}

	@Override
	public Project getProject(final String token) {
		return dao.getProject(token);
	}

}
