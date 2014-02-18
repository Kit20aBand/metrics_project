package com.metrics.persistence.dao.common;

import com.metrics.persistence.model.User;

public interface IUserOperations extends ICommonOperations<User> {

	public User getUser(String login);
}
