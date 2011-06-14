package com.OJToolkit.client.ValueObjects;

/**
 * @author magdi
 *
 */
public class SourceCodeData {
	private Long codeID;
	private Long userID;
	private String problemCode;
	private String problemName;
	private String url;
	private String code;
	private String note;
	private String judgeResult;
	private Long date;
	
	/**
	 * @param codeID
	 * @param userID
	 * @param problemCode
	 * @param problemName
	 * @param url
	 * @param code
	 * @param note
	 * @param judgeResult
	 * @param date
	 */
	public SourceCodeData(Long codeID, Long userID, String problemCode,
			String problemName, String url, String code, String note,
			String judgeResult, Long date) {
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
	/**
	 * @return coderID 
	 */
	public Long getCodeID() {
		return codeID;
	}
	/**
	 * @param codeID
	 */
	public void setCodeID(Long codeID) {
		this.codeID = codeID;
	}
	/**
	 * @return userID 
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
	 * @return ProblemCode
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
	 * @return problemName
	 */
	public String getProblemName() {
		return problemName;
	}
	/**
	 * @param problemName
	 */
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	/**
	 * @return problemUrl
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return ProblemCode 
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return ProblemNotes
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return probleJudgeResult
	 */
	public String getJudgeResult() {
		return judgeResult;
	}
	/**
	 * @param judgeResult
	 */
	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}
	/**
	 * @return submitionDate
	 */
	public Long getDate() {
		return date;
	}
	/**
	 * @param date
	 */
	public void setDate(Long date) {
		this.date = date;
	}

}
