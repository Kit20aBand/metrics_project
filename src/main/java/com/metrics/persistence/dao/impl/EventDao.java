package com.metrics.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IEventDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;

@Repository
public class EventDao extends AbstractHibernateDao<Event> implements IEventDao {

	public EventDao() {
		setClazz(Event.class);
	}

	@Override
	public List<Event> getEvent(final Project project) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("project", project);
		return findWithNamedQuery(Event.FIND_BY_PROJECT, params);
	}

}
