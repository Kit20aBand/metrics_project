package com.metrics.persistence.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = Event.FIND_BY_PROJECT, query = "SELECT e FROM Event e WHERE e.project = :project") })
public class Event extends BaseEntity {

	public static final String FIND_BY_PROJECT = "Event.findByProject";

	private String name;

	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

}
