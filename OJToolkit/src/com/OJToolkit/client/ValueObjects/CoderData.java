package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B Holds the data of the coder for viewing it
 */
@SuppressWarnings("serial")
public class CoderData implements Serializable {
	private Long userID;
	private String username;
	private String email;
	private String SPOJUsername;
	private String SPOJPassword;
	private String TimusUsername;
	private String TimusPassword;
	private String UVAUsername;
	private String UVAPassword;
	
	public CoderData() {

	}

	/**
	 * Creates a new instance of a coder with the following parameters
	 * 
	 * @param userID
	 *            User ID
	 * @param username
	 *            Username
	 * @param email
	 *            Email
	 * @param sPOJUsername
	 *            The username of the user's SPOJ account
	 * @param sPOJPassword
	 *            The password of the user's SPOJ account
	 */
	public CoderData(Long userID, String username, String email,
			String sPOJUsername, String sPOJPassword) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		SPOJUsername = sPOJUsername;

		SPOJPassword = sPOJPassword;
	}

	/**
	 * @return UserID UserID
	 */
	public Long getUserID() {
		return userID;
	}

	/**
	 * Sets the userID
	 * 
	 * @param userID
	 *            userID
	 */
	public void setUserID(Long userID) {
		this.userID = userID;

	}

	/**
	 * @return The username of the coder
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            The username of the coder
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return The Email address of the coder
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The Email address of the coder
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return The username of the user's SPOJ account
	 */
	public String getSPOJUsername() {
		return SPOJUsername;
	}

	/**
	 * @param sPOJUsername
	 *            The username of the user's SPOJ account
	 */
	public void setSPOJUsername(String sPOJUsername) {
		SPOJUsername = sPOJUsername;
	}

	/**
	 * @return The password of the user's SPOJ account
	 */
	public String getSPOJPassword() {
		return SPOJPassword;
	}

	/**
	 * @param sPOJPassword
	 *            The password of the user's SPOJ account
	 */
	public void setSPOJPassword(String sPOJPassword) {
		SPOJPassword = sPOJPassword;
	}

	/**
	 * @return the timusUsername
	 */
	public String getTimusUsername() {
		return TimusUsername;
	}

	/**
	 * @param timusUsername the timusUsername to set
	 */
	public void setTimusUsername(String timusUsername) {
		TimusUsername = timusUsername;
	}

	/**
	 * @return the timusPassword
	 */
	public String getTimusPassword() {
		return TimusPassword;
	}

	/**
	 * @param timusPassword the timusPassword to set
	 */
	public void setTimusPassword(String timusPassword) {
		TimusPassword = timusPassword;
	}

	/**
	 * @return the uVAUsername
	 */
	public String getUVAUsername() {
		return UVAUsername;
	}

	/**
	 * @param uVAUsername the uVAUsername to set
	 */
	public void setUVAUsername(String uVAUsername) {
		UVAUsername = uVAUsername;
	}

	/**
	 * @return the uVAPassword
	 */
	public String getUVAPassword() {
		return UVAPassword;
	}

	/**
	 * @param uVAPassword the uVAPassword to set
	 */
	public void setUVAPassword(String uVAPassword) {
		UVAPassword = uVAPassword;
	}
}
