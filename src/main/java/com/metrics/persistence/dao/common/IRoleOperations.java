package com.metrics.persistence.dao.common;

import com.metrics.persistence.model.Role;

public interface IRoleOperations extends ICommonOperations<Role> {

	public Role getRole(String role);
}
