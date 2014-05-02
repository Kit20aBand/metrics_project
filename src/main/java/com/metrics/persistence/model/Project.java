package com.metrics.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries({
	@NamedQuery(name = Project.FIND_BY_USER, query = "SELECT p FROM Project p WHERE p.user = :user"),
	@NamedQuery(name = Project.FIND_BY_TOKEN, query = "SELECT p FROM Project p WHERE p.token = :token") })
public class Project extends BaseEntity {

	public static final String FIND_BY_USER = "Project.findByUser";

	public static final String FIND_BY_TOKEN = "Project.findByToken";

	private String name;

	private String token;

	@Lob
	@Column(length = 1000)
	private String description;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "project")
	@Cascade({ CascadeType.ALL })
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

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

}
