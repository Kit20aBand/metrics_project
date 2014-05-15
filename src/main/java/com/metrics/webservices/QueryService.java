package com.metrics.webservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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

	@GET
	@Path("/query")
	public Response saveDataGetRequest(
			@Context final ServletContext servletContext,
			@QueryParam("token") final String token,
			@QueryParam("eventName") final String eventName,
			@QueryParam("propertyName") final List<String> propertyNames,
			@QueryParam("propertyValue") final List<String> propertyValues) {

		final ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		eventService = ctx.getBean("eventService", IEventService.class);
		projectService = ctx.getBean("projectService", IProjectService.class);
		propertyService = ctx
				.getBean("propertyService", IPropertyService.class);
		final Project project = projectService.getProject(token);
		if (project == null) {
			return Response.status(404)
					.entity("Project with this token was not found").build();
		}
		final Event event = new Event();
		final List<Property> properties = new ArrayList<Property>();
		Property property;
		for (int i = 0; i < propertyNames.size(); i++) {
			property = new Property();
			property.setEvent(event);
			property.setName(propertyNames.get(i));
			property.setValue(propertyValues.get(i));
			properties.add(property);
		}
		event.setDate(new Date());
		event.setName(eventName);
		event.setProject(project);
		event.setProperties(properties);
		eventService.create(event);
		for (final Property p : properties) {
			propertyService.create(p);
		}
		return Response
				.status(200)
				.entity("Event was saved to project: " + project.getName()
						+ " Event name: " + eventName).build();
	}

	@POST
	@Path("/query")
	public Response saveDataPostRequest(
			@Context final ServletContext servletContext,
			@QueryParam("token") final String token,
			@QueryParam("eventName") final String eventName,
			@QueryParam("propertyName") final List<String> propertyNames,
			@QueryParam("propertyValue") final List<String> propertyValues) {
		return saveDataGetRequest(servletContext, token, eventName,
				propertyNames,
				propertyValues);
	}
}
