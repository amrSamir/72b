package com.OJToolkit_2.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Problem {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long probID;
	
	@Persistent
	private String probName;
	
	@Persistent
	private String URL;
	
	@Persistent
	private String OJ;
	
	@Persistent
	private String probCode;

	public Problem(String probCode, String probName, String uRL, String oJ) {
		super();
		this.probName = probName;
		URL = uRL;
		OJ = oJ;
		this.probCode = probCode;
	}

	public Long getProbID() {
		return probID;
	}

	public String getProbCode() {
		return probCode;
	}

	public void setProbCode(String probCode) {
		this.probCode = probCode;
	}

	public void setProbID(Long probID) {
		this.probID = probID;
	}

	public String getProbName() {
		return probName;
	}

	public void setProbName(String probName) {
		this.probName = probName;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getOJ() {
		return OJ;
	}

	public void setOJ(String oJ) {
		OJ = oJ;
	}

}
