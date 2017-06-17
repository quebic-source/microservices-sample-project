package com.quebic.auth.api.security.dto;

public class JwtAuthenticationDto {

	private String username;
	private String password;

	public JwtAuthenticationDto() {
	}

	public JwtAuthenticationDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
