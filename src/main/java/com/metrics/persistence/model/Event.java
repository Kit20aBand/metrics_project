package com.metrics.persistence.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event extends BaseEntity {

	private String name;

	@ManyToOne
	private Project project;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	Set<Property> properties;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(final Set<Property> properties) {
		this.properties = properties;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(final Project project) {
		this.project = project;
	}

}
