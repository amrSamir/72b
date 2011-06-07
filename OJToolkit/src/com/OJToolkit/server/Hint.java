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

	
	/**
	 * Hint Constractor
	 * @param hintCode
	 * @param problemCode
	 * @param hintString
	 * @param userID
	 */
	public Hint(Long hintCode, String problemCode, String hintString,
			Long userID) {
		super();
		this.hintCode = hintCode;
		this.problemCode = problemCode;
		this.hintString = hintString;
		this.userID = userID;
	}



	/**
	 * @return hint 
	 */
	public Long getHintCode() {
		return hintCode;
	}



	/**
	 * set hint 
	 * @param hintCode
	 */
	public void setHintCode(Long hintCode) {
		this.hintCode = hintCode;
	}



	/**
	 * @return problem code
	 */
	public String getProblemCode() {
		return problemCode;
	}



	/**
	 * @param problemCode
	 */
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}



	/**
	 * @return hintString
	 */
	public String getHintString() {
		return hintString;
	}



	/**
	 * @param hintString
	 */
	public void setHintString(String hintString) {
		this.hintString = hintString;
	}



	/**
	 * @return userid 
	 */
	public Long getUserID() {
		return userID;
	}



	/**
	 * @param userID
	 */
	public void setUserID(Long userID) {
		this.userID = userID;
	}



	public Hint(){}
}