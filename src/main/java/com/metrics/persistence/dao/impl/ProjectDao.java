package com.metrics.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IProjectDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Project;

@Repository
public class ProjectDao extends AbstractHibernateDao<Project> implements
		IProjectDao {

	public ProjectDao() {
		setClazz(Project.class);
	}

}
