package com.metrics.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IPropertyDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Property;

@Repository
public class PropertyDao extends AbstractHibernateDao<Property> implements
IPropertyDao {

	public PropertyDao() {
		setClazz(Property.class);
	}

	@Override
	public List<Property> getProperties(final Event event) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("eventproject", event);
		return findWithNamedQuery(Property.FIND_BY_EVENT, params);
	}

}
