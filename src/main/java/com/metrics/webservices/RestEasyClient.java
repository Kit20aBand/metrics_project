package com.metrics.webservices;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class RestEasyClient {

	public static void main(final String[] args) throws Exception {
		final ClientRequest request = new ClientRequest(
				"http://localhost:8080/metrics/rest/query?token=exCxwbOLPb&eventName=444&propertyName=username&propertyValue=Serhii&propertyName=OS&propertyValue=Android");
		final ClientResponse<String> response = request.post(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		final BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(
						response.getEntity().getBytes())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

	}
}
