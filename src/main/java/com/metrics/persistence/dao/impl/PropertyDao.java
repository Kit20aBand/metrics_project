package com.metrics.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IPropertyDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Property;

@Repository
public class PropertyDao extends AbstractHibernateDao<Property> implements
IPropertyDao {

	public PropertyDao() {
		setClazz(Property.class);
	}

}
