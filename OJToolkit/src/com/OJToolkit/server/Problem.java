package com.OJToolkit.server;

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
	private String url;

	@Persistent
	private String problemCode;

	@Persistent
	private String problemName;
	
	@Persistent
	private String ojType;



	public Problem(String problemCode,
	        String problemName,String url, String ojType) {
		super();
		this.url = url;
		this.problemCode = problemCode;
		this.problemName = problemName;
		this.ojType = ojType;
	}
	
	public String getProblemCode() {
		return problemCode;
	}

	public Long getProbID() {
		return probID;
	}

	public void setProbID(Long probID) {
		this.probID = probID;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public Problem(String u) {
		url = u;
	}

	public Problem() {
	}



	/**
     * @param ojType the ojType to set
     */
    public void setOjType(String ojType) {
	    this.ojType = ojType;
    }

	/**
     * @return the ojType
     */
    public String getOjType() {
	    return ojType;
    }

	/**
     * @return the url
     */
    public String getUrl() {
    	return url;
    }

	/**
     * @param url the url to set
     */
    public void setUrl(String url) {
    	this.url = url;
    }


}
