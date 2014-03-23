package com.metrics.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("xml/user")
public class XMLService {

	@GET
	@Path("/get")
	@Produces("application/xml")
	public User getUserInXml() {
		final User user = new User();
		user.setUsername("Serhii_Nosko");
		user.setPassword("Cortgb334a");
		user.setPin(123456);
		return user;
	}
}
