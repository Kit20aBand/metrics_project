package com.metrics.webservices;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class RestEasyClient {

	public static void main(final String[] args) throws Exception {
		final ClientRequest request = new ClientRequest(
				"http://localhost:8080/metrics/rest/query?from=100&to=200&orderBy=age&orderBy=name");
		final String input
		final ClientResponse<String> response = request.post(String.class);
	}

}
