package com.metrics.persistence.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = Event.FIND_BY_PROJECT, query = "SELECT e FROM Event e WHERE e.project = :project"),
	@NamedQuery(name = Event.FIND_BY_PROJECT_WITHOUT_DUPLICATES, query = "SELECT e FROM Event e WHERE e.project = :project GROUP BY e.name"),
	@NamedQuery(name = Event.GET_COUNT_OF_EVENTS, query = "SELECT COUNT(e) FROM Event e where e.project = :project AND e.name = :name") })
public class Event extends BaseEntity {

	public static final String FIND_BY_PROJECT = "Event.findByProject";

	public static final String FIND_BY_PROJECT_WITHOUT_DUPLICATES = "Event.findByProjectWithoutDuplicates";

	public static final String GET_COUNT_OF_EVENTS = "Event.getCount";

	private String name;

	private Date date;

	@ManyToOne
	private Project project;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private List<Property> properties;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
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

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(final List<Property> properties) {
		this.properties = properties;
	}

}
