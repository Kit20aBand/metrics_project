package com.metrics.webservices;

import java.util.Arrays;

public class JsonEvent {

	private String name;

	private JsonProperty[] properties;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public JsonProperty[] getProperties() {
		return properties;
	}

	public void setProperties(final JsonProperty[] properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", properties="
				+ Arrays.toString(properties) + "]";
	}

}
