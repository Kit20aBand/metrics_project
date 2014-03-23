package com.metrics.view.datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.metrics.persistence.model.User;

public class LazyUserDataModel extends LazyDataModel<User> {

	private List<User> datasource;

	public LazyUserDataModel(final List<User> datasource) {
		this.datasource = datasource;
	}

	@Override
	public User getRowData(final String rowKey) {
		for (final User user : datasource) {
			if (user.getId().toString().equals(rowKey))
				return user;
		}

		return null;
	}

	@Override
	public Object getRowKey(final User User) {
		return User.getId();
	}

	@Override
	public List<User> load(final int first, final int pageSize,
			final String sortField, final SortOrder sortOrder,
			final Map<String, String> filters) {
		final List<User> data = new ArrayList<User>();

		//filter
		for (final User User : datasource) {
			boolean match = true;

			for (final Iterator<String> it = filters.keySet().iterator(); it
					.hasNext();) {
				try {
					final String filterProperty = it.next();
					final String filterValue = filters.get(filterProperty);
					final String fieldValue = String.valueOf(User.getClass().getField(filterProperty).get(User));

					if(filterValue == null || fieldValue.startsWith(filterValue)) {
						match = true;
					}
					else {
						match = false;
						break;
					}
				} catch(final Exception e) {
					match = false;
				}
			}

			if(match) {
				data.add(User);
			}
		}

		//sort
		if(sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		//rowCount
		final int dataSize = data.size();
		setRowCount(dataSize);

		//paginate
		if(dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			}
			catch(final IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		}
		else {
			return data;
		}
	}
}
