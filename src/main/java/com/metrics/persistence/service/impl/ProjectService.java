package com.metrics.persistence.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.metrics.persistence.dao.IProjectDao;
import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Project;
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

}
