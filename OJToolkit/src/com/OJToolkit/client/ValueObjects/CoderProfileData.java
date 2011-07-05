package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B Holds the data of the coder for viewing it in his profile
 */
@SuppressWarnings("serial")
public class CoderProfileData implements Serializable {
	private String username;
	private String email;
	private String SPOJUsername;
	private String UVAUsername;
	private String TimusUsername;
	private int numberOfSubmission;
	private int numberOfSolved;

	public CoderProfileData() {

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
	public CoderProfileData(String username, String email, String sPOJUsername,
            String uVAUsername, String timusUsername, int numberOfSubmission,
            int numberOfSolved) {
	    super();
	    this.username = username;
	    this.email = email;
	    SPOJUsername = sPOJUsername;
	    UVAUsername = uVAUsername;
	    TimusUsername = timusUsername;
	    this.numberOfSubmission = numberOfSubmission;
	    this.numberOfSolved = numberOfSolved;
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

	}
