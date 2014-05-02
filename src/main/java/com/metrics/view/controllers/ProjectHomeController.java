package com.metrics.view.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.service.IEventService;
import com.metrics.util.ThingsOverWhichIsWorking;

@Named
@Scope("request")
public class ProjectHomeController {

	@Inject
	private IEventService eventService;

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	private List<Event> events;

	private Event selectedEvent;

	private List<Event> filteredEvents;

	@PostConstruct
	public void init() {
		final Project project = thingsOverWhichIsWorking.getActiveProject();
		events = eventService.getEvent(project);
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(final List<Event> events) {
		this.events = events;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(final Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public List<Event> getFilteredEvents() {
		return filteredEvents;
	}

	public void setFilteredEvents(final List<Event> filteredEvents) {
		this.filteredEvents = filteredEvents;
	}

}
