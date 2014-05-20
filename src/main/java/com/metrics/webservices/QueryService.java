package com.metrics.webservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.model.Property;
import com.metrics.persistence.service.IEventService;
import com.metrics.persistence.service.IProjectService;
import com.metrics.persistence.service.IPropertyService;

@Path("/rest/")
public class QueryService {

	private static final Log log = LogFactory.getLog(QueryService.class);

	private IEventService eventService;

	private IProjectService projectService;

	private IPropertyService propertyService;

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response saveEvents(@Context final ServletContext servletContext,
			final JsonEvents events) {
		final ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		eventService = ctx.getBean("eventService", IEventService.class);
		projectService = ctx.getBean("projectService", IProjectService.class);
		propertyService = ctx
				.getBean("propertyService", IPropertyService.class);

		final Project project = projectService.getProject(events.getToken());
		if (project == null) {
			return Response.status(404)
					.entity("Project with this token was not found").build();
		}
		Event event;
		Property property;
		final List<Property> properties = new ArrayList<Property>();
		for (final JsonEvent jsonEvent : events.getEvents()) {
			event = new Event();
			event.setProject(project);
			event.setName(jsonEvent.getName());
			event.setDate(new Date());

			for (final JsonProperty jsonProperty : jsonEvent.getProperties()) {
				property = new Property();
				property.setEvent(event);
				property.setName(jsonProperty.getName());
				property.setValue(jsonProperty.getValue());
				properties.add(property);
			}

			event.setProperties(properties);

			eventService.create(event);

			for (final Property p : properties) {
				propertyService.create(p);
			}
		}

		return Response.status(200)
				.entity("Events was saved to project: " + events)
				.build();
	}
}
