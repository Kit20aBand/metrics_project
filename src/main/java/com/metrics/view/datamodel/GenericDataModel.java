package com.metrics.view.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.BaseEntity;

@Component
public class GenericDataModel<T extends BaseEntity> extends LazyDataModel<T>
implements SelectableDataModel<T>, Serializable {

	private static final Log log = LogFactory.getLog(GenericDataModel.class);

	private ICommonOperations<T> service;

	private String namedQuery;

	private Map<String, Object> params;

	private List<T> datasource;


	public GenericDataModel(final ICommonOperations<T> service) {
		this.service = service;
	}

	public GenericDataModel() {

	}


	@Override
	public List<T> load(final int first, final int pageSize,
			final String sortField, final SortOrder sortOrder,
			final Map<String, String> filters) {
		datasource = service.findWithNamedQuery(namedQuery, params, first,
				first + pageSize);
		log.info(String.format("Data was load from index %d to index %d",
				first, first + pageSize));

		final List<T> data = new ArrayList<T>();

		// filter
		for (final T t : datasource) {
			boolean match = true;


	            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
	                try {  
	                    String filterProperty = it.next();  
					final String filterValue = filters.get(filterProperty);
					final String fieldValue = String.valueOf(t.getClass()
							.getField(filterProperty).get(t));

					if (filterValue == null
							|| fieldValue.startsWith(filterValue)) {
						match = true;
					} else {
						match = false;
						break;
					}
				} catch (final Exception e) {
					match = false;
				}
			}

			if (match) {
				data.add(t);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LazySorter(sortField, sortOrder));
		}

		// rowCount
		final int dataSize = data.size();
		setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (final IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}

	}



	@Override
	public T getRowData(final String rowKey) {
		for (final T t : datasource) {
			log.info(t.getId());
			if (t.getId().toString().equals(rowKey))
				return t;
		}

		return null;
	}

	@Override
	public Object getRowKey(final T t) {
		log.info(t.getId());
		return t.getId().toString();
	}

	public void setNamedQuery(final String namedQuery) {
		this.namedQuery = namedQuery;
	}

	public void setParams(final Map<String, Object> params) {
		this.params = params;
	}

	public void delete(final T t) {
		datasource.remove(t);
	}

	@Override
	public void setRowIndex(final int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		}
		else
			super.setRowIndex(rowIndex % getPageSize());
	}

}
