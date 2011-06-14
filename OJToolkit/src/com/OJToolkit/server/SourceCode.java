package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SourceCode {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long codeID;
	
	@Persistent
	private Long userID;
	@Persistent
	private String problemCode;
	@Persistent
	private String problemName;
	@Persistent
	private String url;
	@Persistent
	private String code;
	@Persistent
	private String note;
	@Persistent
	private String judgeResult;
	@Persistent
	private Long date;
	public SourceCode(Long codeID, Long userID, String problemCode,
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
	public SourceCode(){}
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
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	
	 
}
