package com.metrics.persistence.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries({
		@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
	@NamedQuery(name = User.FIND_BY_LOGIN, query = "SELECT u FROM User u WHERE u.login = :login"),
	@NamedQuery(name = User.TOTAL, query = "SELECT COUNT(u) FROM User u") })
public class User extends BaseEntity {

	public static final String FIND_ALL = "User.findAll";

	public static final String FIND_BY_LOGIN = "User.findByLogin";

	public static final String TOTAL = "User.Total";

	private String login;

	private String password;

	private String email;

	private String firstname;

	private String lastname;

	@OneToOne
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	@Cascade({ CascadeType.ALL })
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Project> projects;

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(final Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(final Set<Project> projects) {
		this.projects = projects;
	}
}
