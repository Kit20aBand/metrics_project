package com.metrics.view.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.metrics.persistence.model.BaseEntity;

public class GenericNoLazyDataModel<T extends BaseEntity> extends
ListDataModel<T> implements SelectableDataModel<T>, Serializable {
	private static final long serialVersionUID = 1L;

	private List<T> data = new ArrayList<T>();

	public GenericNoLazyDataModel() {

	}

	public List<T> getData() {
		return data;
	}

	public void setData(final List<T> data) {
		this.data = data;
	}

	public GenericNoLazyDataModel(final List<T> data) {
		super();
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getRowData(final String rowKey) {

		final List<T> entities = (List<T>) getWrappedData();

		for (final T t : entities) {
			if (t.getId().toString().equals(rowKey))
				return t;
		}

		return null;
	}

	@Override
	public Object getRowKey(final T object) {
		return object.getId().toString();
	}
}
