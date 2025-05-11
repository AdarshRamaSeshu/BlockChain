package com.Project.Blochchain.DTO;


public class LoginResponse {
	
	private String token;

	public LoginResponse(String jwt_token) {
		this.token=jwt_token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
