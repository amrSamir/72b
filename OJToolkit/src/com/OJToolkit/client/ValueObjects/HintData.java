package com.OJToolkit.client.ValueObjects;

public class HintData {
	private Long hintCode;
	private String problemCode;
	private String hintString;

	private Long userID;
 
	
	
	/**
	 * @param hintCode 
	 * @param problemCode
	 * @param hintString
	 * @param userID
	 */
	public HintData(Long hintCode, String problemCode, String hintString,
			Long userID) {
		super();
		this.hintCode = hintCode;
		this.problemCode = problemCode;
		this.hintString = hintString;
		this.userID = userID;
	}



	/**
	 * @return code hint
	 */
	public Long getHintCode() {
		return hintCode;
	}



	/**
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
	 * @return hint 
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
	 * @return user id 
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



	/**
	 * 
	 */
	public HintData(){}
}
