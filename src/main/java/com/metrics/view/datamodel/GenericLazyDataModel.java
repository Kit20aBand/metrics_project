package com.metrics.view.datamodel;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.metrics.persistence.model.BaseEntity;

public class GenericLazyDataModel<T extends BaseEntity> extends LazyDataModel<T> implements Serializable {

	private List<T> datasource;

	public GenericLazyDataModel() {

	}

	public GenericLazyDataModel(final List<T> datasource) {
		this.datasource = datasource;
	}

	@Override
	public T getRowData(final String rowKey) {
		checkNotNull(datasource);
		for (final T t: datasource) {
			if (t.getId().toString().equals(rowKey)) {
				return t;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(final T object) {
		return object.getId();
	}

	@Override
	public List<T> load(final int first, final int pageSize, final String sortField,
			final SortOrder sortOrder, final Map<String, String> filters) {
		return datasource;
		/*
		 * final List<T> data = new ArrayList<T>(); // filter for (final T t:
		 * datasource) { boolean match = true;
		 * 
		 * for (final String filterProperty : filters.keySet()) { try { final
		 * String filterProperty = filterProperty; final String filterValue =
		 * filters.get(filterProperty); final String fieldValue =
		 * String.valueOf(t.getClass().getField(filterProperty).get(t));
		 * 
		 * if(filterValue == null || fieldValue.startsWith(filterValue)) { match
		 * = true; } else { match = false; break; } } catch(final Exception e) {
		 * match = false; } }
		 * 
		 * if(match) { data.add(t); }
		 * 
		 * // sort if(sortField != null) { Collections.sort(data, new
		 * CopyOfLazySorter(sortField, sortOrder)); } }
		 */
	}






}
