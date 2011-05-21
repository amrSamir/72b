package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B
 *         Holds the data of the logged in user
 */
@SuppressWarnings("serial")
public class LoginInfo implements Serializable {

	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	private String nickname;

	/**
	 * @return Returns whether the selected user is logged in or not
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn
	 *            sets the selected user to be logged in
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the login URL of Gmail
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * @param loginUrl
	 *            the login URL of Gmail
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * @return The logout URL of Gmail
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * @param logoutUrl
	 *            The logout URL of Gmail
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * @return Email Address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            Email Address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return The nickname of the user on 72B
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            The nickname of the user on 72B
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}