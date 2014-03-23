package com.metrics.view.datamodel;

import org.primefaces.model.SortOrder;

import com.metrics.persistence.model.User;

public class UserLazySorter extends LazySorter<User> {

	public UserLazySorter(final String sortField, final SortOrder sortOrder) {
		super(sortField, sortOrder);
		setClazz(User.class);
	}

}
