package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Coder {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long userID;

	@Persistent
	private String username;

	@Persistent
	private String email; 

	@Persistent
	private String SPOJUsername;

	@Persistent
	private String SPOJPassword;
	
	@Persistent
	private String TimusUsername;

	@Persistent
	private String TimusPassword;
	
	@Persistent
	private String UVAUsername;

	@Persistent
	private String UVAPassword;

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

	/**
	 * Contractor for the Coder
	 * @param username
	 * @param email
	 * @param SPOJUsername
	 * @param SPOJPassword
	 */
	public Coder(String username, String email, String SPOJUsername,
	        String SPOJPassword) {
		this.username = username;
		this.email = email;
		this.SPOJUsername = SPOJUsername;
		this.SPOJPassword = SPOJPassword;
	}

	public Coder() {

	}

}
