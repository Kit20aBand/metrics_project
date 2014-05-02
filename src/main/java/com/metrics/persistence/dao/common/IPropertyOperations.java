package com.metrics.persistence.dao.common;

import java.util.List;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Property;

public interface IPropertyOperations extends ICommonOperations<Property> {

	public List<Property> getProperties(Event event);
}
