package com.OJToolkit_2.shared;

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






	public Coder(String username, String email, String SPOJUsername,String SPOJPassword) {
		this.username = username;
		this.email = email;
		this.SPOJUsername = SPOJUsername;
		this.SPOJPassword = SPOJPassword;
	}
	
	
}
