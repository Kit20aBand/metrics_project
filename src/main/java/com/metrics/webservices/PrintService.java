package com.metrics.webservices;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IUserService;

@Path("/rest/")
public class PrintService {

	IUserService iUserService;

	@GET
	@Path("/print")
	public Response printMessage(@Context final ServletContext servletContext) {
		final ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		iUserService = ctx.getBean("userService", IUserService.class);

		final User user = iUserService.findOne(new Integer(1));
		return Response.status(200).entity(user.getId()).build();
	}

}
