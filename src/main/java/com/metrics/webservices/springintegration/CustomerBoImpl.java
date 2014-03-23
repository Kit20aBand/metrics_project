package com.metrics.webservices.springintegration;

import org.springframework.stereotype.Component;

@Component
public class CustomerBoImpl implements CustomerBo {

	@Override
	public String getMsg() {
		return "RESTEasy + Spring example";
	}

}
