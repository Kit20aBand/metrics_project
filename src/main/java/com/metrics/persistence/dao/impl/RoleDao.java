package com.metrics.persistence.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.metrics.persistence.dao.IRoleDao;
import com.metrics.persistence.dao.common.AbstractHibernateDao;
import com.metrics.persistence.model.Role;

@Repository
public class RoleDao extends AbstractHibernateDao<Role> implements IRoleDao {

	public RoleDao() {
		setClazz(Role.class);
	}

	@Override
	public Role getRole(final String role) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("role", role);
		return (Role) getOneResult(Role.FIND_BY_ROLENAME, parameters);
	}
}
