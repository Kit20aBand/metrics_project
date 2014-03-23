package com.metrics.view.datamodel;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.metrics.persistence.model.BaseEntity;

public class LazySorter<T extends BaseEntity> implements Comparator<T> {

	private Class<T> clazz;

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(final String sortField, final SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = checkNotNull(clazzToSet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(final T o1, final T o2) {
		try {
			final Object value1 = clazz.getField(this.sortField).get(o1);
			final Object value2 = clazz.getField(this.sortField).get(o2);

			final int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (final Exception e) {
			throw new RuntimeException();
		}
	}

}
