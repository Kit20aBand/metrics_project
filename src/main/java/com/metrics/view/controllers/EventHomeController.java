package com.metrics.view.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Property;
import com.metrics.persistence.service.IPropertyService;
import com.metrics.util.ThingsOverWhichIsWorking;

@Named
@Scope("request")
public class EventHomeController {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	private IPropertyService propertyService;

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	private Property selectedProperty;

	private List<Property> properties;

	private List<Property> filteredProperties;

	@PostConstruct
	public void init() {
		properties = propertyService.getProperties(thingsOverWhichIsWorking
				.getActiveEvent());
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(final List<Property> properties) {
		this.properties = properties;
	}

	public List<Property> getFilteredProperties() {
		return filteredProperties;
	}

	public void setFilteredProperties(final List<Property> filteredProperties) {
		this.filteredProperties = filteredProperties;
	}

	public Property getSelectedProperty() {
		return selectedProperty;
	}

	public void setSelectedProperty(final Property selectedProperty) {
		this.selectedProperty = selectedProperty;
	}
}
