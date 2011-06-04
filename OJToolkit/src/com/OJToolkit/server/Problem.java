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
	private String type;

	@Persistent
	private String problemCode;

	@Persistent
	private String problemName;

	public Problem(String url, String type, String problemCode,
	        String problemName) {
		super();
		this.url = url;
		this.type = type;
		this.problemCode = problemCode;
		this.problemName = problemName;
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

	public void setType(String t) {
		type = t;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
