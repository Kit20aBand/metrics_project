package com.metrics.persistence.dao.common;

import java.util.List;

import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;

public interface IUserOperations extends ICommonOperations<User> {

	public List<User> getUsersByRole(final Role role);

	public User getUser(final String username);
}
