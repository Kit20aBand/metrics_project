package com.metrics.view.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Role;
import com.metrics.persistence.model.User;
import com.metrics.persistence.service.IRoleService;
import com.metrics.persistence.service.IUserService;

@Named
@Scope("request")
public class AllUsersController {

	private static final Log log = LogFactory.getLog(AllUsersController.class);

	@Inject
	private IUserService userService;

	@Inject
	private IRoleService roleService;

	private List<User> users;

	private User selectedUser;

	private List<User> filteredUsers;

	@PostConstruct
	public void init() {
		final Role role = roleService.getRole(Role.ROLE_USER);
		users = userService.getUsersByRole(role);
		log.info(users);
	}

	public void delete() {
		log.info("Delete");
		users.remove(selectedUser);
		userService.delete(selectedUser);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(final List<User> users) {
		this.users = users;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(final List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(final User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
