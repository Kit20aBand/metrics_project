package com.metrics.webservices.springintegration;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Path("/customer")
public class PrintService {

	CustomerBo customerBo;

	@GET
	@Path("/print")
	public Response printMessage(@Context final ServletContext servletContext) {
		final ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		customerBo = ctx.getBean("customerBoImpl", CustomerBoImpl.class);
		final String result = customerBo.getMsg();
		return Response.status(200).entity(result).build();
	}
}
