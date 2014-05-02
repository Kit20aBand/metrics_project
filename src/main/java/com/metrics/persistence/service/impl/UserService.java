package com.metrics.persistence.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.metrics.persistence.dao.IUserDao;
import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IUserService;
import com.metrics.persistence.service.common.AbstractService;

@Service
public class UserService extends AbstractService<User> implements IUserService {

	@Inject
	private IUserDao dao;

	@Override
	protected ICommonOperations<User> getDao() {
		return dao;
	}

	@Override
	public List<User> getUsersByRole(final Role role) {
		return dao.getUsersByRole(role);
	}

	@Override
	public User getUser(final String username) {
		return dao.getUser(username);
	}


}
