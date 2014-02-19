package com.metrics.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project extends BaseEntity {

	private String name;

	@Lob
	@Column(length = 1000)
	private String description;

	@ManyToOne
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private Set<Event> events;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(final Set<Event> events) {
		this.events = events;
	}

}
