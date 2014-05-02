package com.metrics.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;

@Component
@Scope("session")
public class ThingsOverWhichIsWorking {

	private Project project;

	private Event event;

	public Project getActiveProject() {
		return project;
	}

	public void setActiveProject(final Project project) {
		this.project = project;
	}

	public Event getActiveEvent() {
		return event;
	}

	public void setActiveEvent(final Event event) {
		this.event = event;
	}
}
