package com.metrics.persistence.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.metrics.persistence.dao.IEventDao;
import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Event;
import com.metrics.persistence.service.IEventService;
import com.metrics.persistence.service.common.AbstractService;

@Service
public class EventService extends AbstractService<Event> implements
IEventService {

	@Inject
	private IEventDao dao;

	@Override
	protected ICommonOperations<Event> getDao() {
		return dao;
	}

	@Override
	public List<Event> findWithNamedQuery(final String namedQueryName,
			final int start, final int end) {
		// TODO Auto-generated method stub
		return null;
	}

}