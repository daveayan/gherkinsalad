package org.openqa.selenium.security;

import java.security.Principal;



public class UserAndPassword {
	private String username, password;
	public UserAndPassword(java.lang.String username, java.lang.String password)  {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Principal getUserPrincipal() {
		return null;
	}
}