package com.OJToolkit_2.client.ValueObjects;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CoderData implements Serializable {

	public CoderData() {

	}

	public CoderData(Long userID, String username, String email,
			String sPOJUsername, String sPOJPassword) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		SPOJUsername = sPOJUsername;
		SPOJPassword = sPOJPassword;
	}

	private Long userID;

	private String username;

	private String email;

	private String SPOJUsername;

	private String SPOJPassword;

	public Long getUserID() {
		return userID;
	}

	public String getSPOJUsername() {
		return SPOJUsername;
	}

	public void setSPOJUsername(String sPOJUsername) {
		SPOJUsername = sPOJUsername;
	}

	public String getSPOJPassword() {
		return SPOJPassword;
	}

	public void setSPOJPassword(String sPOJPassword) {
		SPOJPassword = sPOJPassword;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
