package com.metrics.persistence.dao.common;

import java.util.List;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;

public interface IEventOperations extends ICommonOperations<Event> {

	public List<Event> getEvent(Project project);

	public List<Event> getEventsWithoutDuplicates(Project project);

	public int getCountOfEvents(Project project, String eventName);
}
