package com.metrics.webservices;

public class JsonProperty {

	private String name;

	private String value;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", value=" + value + "]";
	}
}
