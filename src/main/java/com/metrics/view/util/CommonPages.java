package com.metrics.view.util;

public class CommonPages {

	// All

	public static final String indexPage = "/pages/unsecure/index.xhtml";
	public static final String loginPage = "/pages/unsecure/login.xhtml";
	public static final String createAccountPage = "/pages/unsecure/create_account.xhtml";

	// ADMIN

	public static final String consolePage = "/pages/secure/admin/console.xhtml";
	public static final String usersPage = "/pages/secure/admin/users.xhtml";

	// USER

	public static final String userHomePage = "/pages/secure/user/user_home.xhtml";
	public static final String userCreateProjectPage = "/pages/secure/user/create_project.xhtml";
	public static final String userProjectPage = "/pages/secure/user/project_home.xhtml";
	public static final String userAllProjectsPage = "/pages/secure/user/projects.xhtml";

	private CommonPages() {

	}

}
