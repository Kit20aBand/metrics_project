package com.metrics.persistence.model;

import javax.persistence.ManyToOne;

public class Property extends BaseEntity {

	private String name;

	private double value;

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

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

}
