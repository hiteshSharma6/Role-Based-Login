package org.firstvision.restapi.security.auth.jwt.model;

import java.util.Date;

public class JwtTokenDetail {
	private String username;
	private Date expirationDate;

	public JwtTokenDetail() {
		super();
	}

	public JwtTokenDetail(String username, Date expirationDate) {
		super();
		this.username = username;
		this.expirationDate = expirationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
