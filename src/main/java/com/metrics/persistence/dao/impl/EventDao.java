package com.metrics.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IEventDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Event;

@Repository
public class EventDao extends AbstractHibernateDao<Event> implements IEventDao {

	public EventDao() {
		setClazz(Event.class);
	}

}
