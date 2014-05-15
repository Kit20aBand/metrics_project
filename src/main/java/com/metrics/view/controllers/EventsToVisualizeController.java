package com.metrics.view.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.service.IEventService;
import com.metrics.util.ThingsOverWhichIsWorking;
import com.metrics.view.util.CommonPages;

@Named
@Scope("request")
public class EventsToVisualizeController {

	private static final Log log = LogFactory
			.getLog(EventsToVisualizeController.class);

	@Inject
	private IEventService eventService;

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	private List<Event> events;

	private List<Event> selectedEvents;

	private List<Event> filteredEvents;

	@PostConstruct
	public void init() {
		final Project project = thingsOverWhichIsWorking.getActiveProject();
		events = eventService.getEventsWithoutDuplicates(project);
	}

	public String visualizate() {
		thingsOverWhichIsWorking.setEventsToVisualize(selectedEvents);
		return CommonPages.USER_VISUZLIZATION_PAGE + "?faces-redirect=true";
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(final List<Event> events) {
		this.events = events;
	}

	public List<Event> getSelectedEvents() {
		return selectedEvents;
	}

	public void setSelectedEvents(final List<Event> selectedEvents) {
		this.selectedEvents = selectedEvents;
	}

	public List<Event> getFilteredEvents() {
		return filteredEvents;
	}

	public void setFilteredEvents(final List<Event> filteredEvents) {
		this.filteredEvents = filteredEvents;
	}

}
