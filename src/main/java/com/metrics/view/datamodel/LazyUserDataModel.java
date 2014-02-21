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

import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IUserService;
import com.metrics.view.controllers.AllUsersController;

@Component
public class LazyUserDataModel extends LazyDataModel<User> implements
Serializable {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	private IUserService service;

	private List<User> datasource;

	private int pageSize;

	private int rowIndex;

	private int rowCount;

	public LazyUserDataModel() {

	}

	public LazyUserDataModel(final IUserService service) {
		this.service = service;
	}

	@Override
	public List<User> load(final int first, final int pageSize,
			final String sortField, final SortOrder sortOrder,
			final Map<String, String> filters) {
		datasource = service.findWithNamedQuery(User.FIND_ALL, first, first
				+ pageSize);
		log.info("Data from DB was load");
		System.out.println(datasource);
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
	public Object getRowKey(final User user) {
		return user.getId().toString();
	}

	@Override
	public User getRowData() {
		checkNotNull(datasource);
		final int index = rowIndex % pageSize;
		if (index > datasource.size()) {
			return null;
		}
		return datasource.get(index);
	}

	@Override
	public User getRowData(final String rowKey) {
		checkNotNull(datasource);
		for (final User user : datasource) {
			if (user.getId().toString().equals(rowKey))
				return user;
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
		datasource = (List<User>) list;
	}
}
