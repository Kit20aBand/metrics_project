package com.metrics.persistence.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries({ @NamedQuery(name = Role.FIND_BY_ROLENAME, query = "SELECT r FROM Role r WHERE r.role = :role") })
public class Role extends BaseEntity {

	public static final String FIND_BY_ROLENAME = "Role.findByRole";

	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final String ROLE_USER = "ROLE_USER";

	private String role;

	@OneToMany()
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
	@Cascade({ CascadeType.ALL })
	private Set<User> users;

	public Role() {

	}

	public Role(final String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(final Set<User> users) {
		this.users = users;
	}
}
