package com.metrics.webservices;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.service.IEventService;
import com.metrics.persistence.service.IProjectService;

@Path("/rest/")
public class QueryService {

	private IEventService eventService;

	private IProjectService projectService;

	@GET
	@Path("/query")
	public Response getUsers(@Context final ServletContext servletContext,
			@QueryParam("from") final int from, @QueryParam("to") final int to,
			@QueryParam("orderBy") final List<String> orderBy) {

		final ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		eventService = ctx.getBean("eventService", IEventService.class);
		projectService = ctx.getBean("projectService", IProjectService.class);

		final String token = "kuptwWivZs";
		final Project project = projectService.getProject(token);
		final Event event = new Event();
		event.setDate(new Date());
		event.setName("someName");
		event.setProject(project);
		// event.setProperties(properties);
		eventService.create(event);
		return Response
				.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy" + orderBy.toString()).build();

	}

	@POST
	@Path("/query")
	public Response getUsersPost(@QueryParam("from") final int from,
			@QueryParam("to") final int to,
			@QueryParam("orderBy") final List<String> orderBy) {

		return Response
				.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to
						+ ", orderBy" + orderBy.toString()).build();

	}
}
