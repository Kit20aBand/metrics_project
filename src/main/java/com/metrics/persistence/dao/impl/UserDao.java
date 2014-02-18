package com.metrics.persistence.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IUserDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.User;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {

	public UserDao() {
		setClazz(User.class);
	}

	@Override
	public User getUser(final String login) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
		return (User) getOneResult(User.FIND_BY_LOGIN, parameters);
	}

}
