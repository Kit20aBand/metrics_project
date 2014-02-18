package com.metrics.persistence.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.metrics.persistence.dao.IRoleDao;
import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.Role;
import com.metrics.persistence.service.IRoleService;
import com.metrics.persistence.service.common.AbstractService;

@Service
public class RoleService extends AbstractService<Role> implements IRoleService {

	@Inject
	private IRoleDao dao;


	@Override
	public Role getRole(final String role) {
		return dao.getRole(role);
	}

	@Override
	protected ICommonOperations<Role> getDao() {
		return dao;
	}

}
