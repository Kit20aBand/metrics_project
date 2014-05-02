package com.metrics.persistence.dao.common;

import java.util.List;

import com.metrics.persistence.model.Project;
import com.metrics.persistence.model.User;

public interface IProjectOperations extends ICommonOperations<Project> {

	public List<Project> getProjects(User user);

	public Project getProject(String token);
}
