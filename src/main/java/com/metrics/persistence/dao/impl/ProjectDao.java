package com.metrics.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IProjectDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.model.User;

@Repository
public class ProjectDao extends AbstractHibernateDao<Project> implements
IProjectDao {

	public ProjectDao() {
		setClazz(Project.class);
	}

	@Override
	public List<Project> getProjects(final User user) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		return findWithNamedQuery(Project.FIND_BY_USER, params);
	}

	@Override
	public Project getProject(final String token) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		return getOneResult(Project.FIND_BY_TOKEN, params);
	}

}
