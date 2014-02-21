package com.metrics.view.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.metrics.persistence.model.User;

public class UserDataModel extends ListDataModel<User> implements
SelectableDataModel<User>, Serializable {

	private List<User> data = new ArrayList<User>();

	public UserDataModel() {

	}

	public List<User> getData() {
		return data;
	}

	public void setData(final List<User> data) {
		this.data = data;
	}

	public UserDataModel(final List<User> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getRowData(final String rowKey) {

		final List<User> users = (List<User>) getWrappedData();
		Integer i;
		for (final User user : users) {
			i = user.getId();
			if (i.equals(rowKey))
				return user;
		}
		return null;
	}

	@Override
	public Object getRowKey(final User user) {
		return user.getId();
	}

}
