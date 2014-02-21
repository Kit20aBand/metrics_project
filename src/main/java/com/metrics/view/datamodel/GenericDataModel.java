package com.metrics.view.datamodel;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Component;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.BaseEntity;
import com.metrics.persistence.model.User;

@Component
public class GenericDataModel<T extends BaseEntity> extends LazyDataModel<T>
implements
Serializable {

	private static final Log log = LogFactory.getLog(GenericDataModel.class);

	private ICommonOperations<T> service;

	private List<T> datasource;

	private int pageSize;

	private int rowIndex;

	private int rowCount;

	public GenericDataModel(final ICommonOperations<T> service) {
		this.service = service;
	}

	public GenericDataModel() {

	}

	@Override
	public List<T> load(final int first, final int pageSize,
			final String sortField, final SortOrder sortOrder,
			final Map<String, String> filters) {
		datasource = service.findAll(first, first
				+ pageSize);
		log.info(String.format("Data was load from index %d to index %d",
				first, first + pageSize));
		setRowCount(service.countTotalRecord(User.TOTAL));
		return datasource;
	}

	@Override
	public boolean isRowAvailable() {
		checkNotNull(datasource);
		final int index = rowIndex % pageSize;
		return index >= 0 && index < datasource.size();
	}

	@Override
	public Object getRowKey(final T user) {
		return user.getId().toString();
	}

	@Override
	public T getRowData() {
		checkNotNull(datasource);
		final int index = rowIndex % pageSize;
		if (index > datasource.size()) {
			return null;
		}
		return datasource.get(index);
	}

	@Override
	public T getRowData(final String rowKey) {
		checkNotNull(datasource);
		for (final T t : datasource) {
			if (t.getId().toString().equals(rowKey))
				return t;
		}
		return null;
	}

	@Override
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getRowIndex() {
		return rowIndex;
	}

	@Override
	public void setRowIndex(final int rowIndex) {
		this.rowIndex = rowIndex;
	}

	@Override
	public void setRowCount(final int rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setWrappedData(final Object list) {
		datasource = (List<T>) list;
	}

}
