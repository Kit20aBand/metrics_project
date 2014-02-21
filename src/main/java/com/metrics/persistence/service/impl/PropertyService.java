package com.metrics.persistence.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.metrics.persistence.dao.IPropertyDao;
import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Property;
import com.metrics.persistence.service.IPropertyService;
import com.metrics.persistence.service.common.AbstractService;

@Service
public class PropertyService extends AbstractService<Property> implements
		IPropertyService {

	@Inject
	private IPropertyDao dao;

	@Override
	protected ICommonOperations<Property> getDao() {
		return dao;
	}

}
