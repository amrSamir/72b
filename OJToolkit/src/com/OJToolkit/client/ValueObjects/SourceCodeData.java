package com.OJToolkit.client.ValueObjects;

public class SourceCodeData {
	private Long codeID;
	private Long userID;
	private String problemCode;
	private String problemName;
	private String url;
	private String code;
	private String note;
	private String judgeResult;
	private String date;
	public SourceCodeData(Long codeID, Long userID, String problemCode,
			String problemName, String url, String code, String note,
			String judgeResult, String date) {
		super();
		this.codeID = codeID;
		this.userID = userID;
		this.problemCode = problemCode;
		this.problemName = problemName;
		this.url = url;
		this.code = code;
		this.note = note;
		this.judgeResult = judgeResult;
		this.date = date;
	}
	public Long getCodeID() {
		return codeID;
	}
	public void setCodeID(Long codeID) {
		this.codeID = codeID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getProblemCode() {
		return problemCode;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getJudgeResult() {
		return judgeResult;
	}
	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
