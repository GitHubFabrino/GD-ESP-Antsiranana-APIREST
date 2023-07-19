package com.appli.springjwt.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank // le username n'est peut pas etre null ou contient d'espace
  private String username;
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
