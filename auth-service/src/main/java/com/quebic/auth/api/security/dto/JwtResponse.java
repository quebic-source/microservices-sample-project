package com.quebic.auth.api.security.dto;

public class JwtResponse {

	private String token;
	private String expiration;

	public JwtResponse() {
	}

	public JwtResponse(String token, String expiration) {
		this.token = token;
		this.expiration = expiration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

}
