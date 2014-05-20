package com.metrics.webservices;

import java.util.Arrays;


public class JsonEvents {

	private String token;

	private JsonEvent[] events;

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public JsonEvent[] getEvents() {
		return events;
	}

	public void setEvents(final JsonEvent[] events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "JsonEvents [token=" + token + ", events="
				+ Arrays.toString(events) + "]";
	}

}
