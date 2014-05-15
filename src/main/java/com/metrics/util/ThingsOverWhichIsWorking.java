package com.metrics.util;

import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;

@Named
@Component
@Scope("session")
public class ThingsOverWhichIsWorking {

	private Project project;

	private Event event;

	private List<Event> eventsToVisualize;

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

	public List<Event> getEventsToVisualize() {
		return eventsToVisualize;
	}

	public void setEventsToVisualize(final List<Event> eventsToVisualize) {
		this.eventsToVisualize = eventsToVisualize;
	}
}
