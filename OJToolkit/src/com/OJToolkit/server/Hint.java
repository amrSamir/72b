package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Hint {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long hintCode;
	
	@Persistent
	private String problemCode;
	
	@Persistent
	private String hintString;

	@Persistent
	private Long userID;

	
	public Hint(Long hintCode, String problemCode, String hintString,
			Long userID) {
		super();
		this.hintCode = hintCode;
		this.problemCode = problemCode;
		this.hintString = hintString;
		this.userID = userID;
	}



	public Long getHintCode() {
		return hintCode;
	}



	public void setHintCode(Long hintCode) {
		this.hintCode = hintCode;
	}



	public String getProblemCode() {
		return problemCode;
	}



	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}



	public String getHintString() {
		return hintString;
	}



	public void setHintString(String hintString) {
		this.hintString = hintString;
	}



	public Long getUserID() {
		return userID;
	}



	public void setUserID(Long userID) {
		this.userID = userID;
	}



	public Hint(){}
}