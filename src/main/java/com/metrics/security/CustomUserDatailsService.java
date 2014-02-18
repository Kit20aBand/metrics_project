package com.metrics.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metrics.persistence.dao.IUserDao;
import com.metrics.persistence.model.Role;
import com.metrics.view.controllers.LoginController;

@Service
@Transactional(readOnly = true)
public class CustomUserDatailsService implements UserDetailsService {
	private static final Log log = LogFactory.getLog(LoginController.class);

	@Inject
	private IUserDao dao;

	@Override
	public UserDetails loadUserByUsername(final String login)
			throws UsernameNotFoundException {

		final com.metrics.persistence.model.User domainUser = dao.getUser(login);
		final boolean enabled = true;
		final boolean accountNonExpired = true;
		final boolean credentialsNonExpired = true;
		final boolean accountNonLocked = true;
		return new User(domainUser.getLogin(), domainUser.getPassword(), enabled,
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,
				getAuthorities(domainUser.getRole().getRole()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			final String role) {
		final List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	private List<GrantedAuthority> getGrantedAuthorities(
			final List<String> roles) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (final String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	private List<String> getRoles(final String role) {
		final List<String> roles = new ArrayList<String>();

		if (role.equals(Role.ROLE_ADMIN)) {
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_USER");
		} else if (role.equals(Role.ROLE_USER)) {
			roles.add("ROLE_USER");
		}
		return roles;
	}


}
