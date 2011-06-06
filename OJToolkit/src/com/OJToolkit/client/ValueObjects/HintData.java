package com.OJToolkit.client.ValueObjects;

public class HintData {
	private Long hintCode;
	private String problemCode;
	private String hintString;

	private Long userID;
 
	
	
	public HintData(Long hintCode, String problemCode, String hintString,
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



	public HintData(){}
}
