package com.metrics.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Property.FIND_BY_EVENT, query = "SELECT p FROM Property p WHERE p.event = :event") })
public class Property extends BaseEntity {

	public static final String FIND_BY_EVENT = "Property.findByEvent";

	private String name;

	private String value;

	@ManyToOne
	private Event event;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
