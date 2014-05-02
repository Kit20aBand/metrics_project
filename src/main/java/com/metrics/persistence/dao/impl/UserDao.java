package com.metrics.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IUserDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

	public UserDao() {
		setClazz(User.class);
	}

	@Override
	public User getUser(final String username) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		return getOneResult(User.FIND_BY_USERNAME, params);
	}

	@Override
	public List<User> getUsersByRole(final Role role) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("role", role);
		return findWithNamedQuery(User.FIND_BY_ROLE, params);
	}
}
