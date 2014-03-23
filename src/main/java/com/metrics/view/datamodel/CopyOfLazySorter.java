package com.metrics.view.datamodel;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.metrics.persistence.model.User;

public class CopyOfLazySorter implements Comparator<User> {


	private String sortField;

	private SortOrder sortOrder;

	public CopyOfLazySorter(final String sortField, final SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}


	@SuppressWarnings("unchecked")
	@Override
	public int compare(final User o1, final User o2) {
		try {
			final Object value1 = User.class.getField(sortField).get(o1);
			final Object value2 = User.class.getField(sortField).get(o2);

			final int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (final Exception e) {
			throw new RuntimeException();
		}
	}

}
