package com.metrics.persistence.dao;

import java.util.List;

import com.metrics.persistence.dao.common.IUserOperations;
import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;

public interface IUserDao extends IUserOperations {

	@Override
	public User getUser(final String username);

	@Override
	public List<User> getUsersByRole(final Role role);
}
