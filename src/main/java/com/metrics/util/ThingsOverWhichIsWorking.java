package com.metrics.util;

import javax.enterprise.context.SessionScoped;

import org.springframework.stereotype.Component;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;

@Component
@SessionScoped
public class ThingsOverWhichIsWorking {

	private Project project;

	private Event event;

	public Project getActiveProject() {
		return project;
	}

	public void setActiveProject(final Project project) {
		this.project = project;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}
}
