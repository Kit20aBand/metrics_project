package com.metrics.webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/rest/")
public class QueryService {

	@GET
	@Path("/query")
	public Response getUsers(@QueryParam("from") final int from,
			@QueryParam("to") final int to,
			@QueryParam("orderBy") final List<String> orderBy) {

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
