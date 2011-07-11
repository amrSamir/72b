package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author magdi
 */
public class SourceCodeData implements Serializable{

	private String problemTitle;

	private String problemCode;

	private String username;

	private String judgeResult;

	private String judgeType;

	private String date;

	private String memory;

	private String time;
	private String sourceCode;
	
	public SourceCodeData() {
	    // TODO Auto-generated constructor stub
    }

	public SourceCodeData(String problemTitle, String problemCode,
            String username, String judgeResult, String judgeType, String date,
            String memory, String time, String sourceCode) {
	    super();
	    this.problemTitle = problemTitle;
	    this.problemCode = problemCode;
	    this.username = username;
	    this.judgeResult = judgeResult;
	    this.judgeType = judgeType;
	    this.date = date;
	    this.memory = memory;
	    this.time = time;
	    this.sourceCode = sourceCode;
    }

	/**
     * @return the problemTitle
     */
    public String getProblemTitle() {
    	return problemTitle;
    }

	/**
     * @param problemTitle the problemTitle to set
     */
    public void setProblemTitle(String problemTitle) {
    	this.problemTitle = problemTitle;
    }

	/**
     * @return the problemCode
     */
    public String getProblemCode() {
    	return problemCode;
    }

	/**
     * @param problemCode the problemCode to set
     */
    public void setProblemCode(String problemCode) {
    	this.problemCode = problemCode;
    }

	/**
     * @return the username
     */
    public String getUsername() {
    	return username;
    }

	/**
     * @param username the username to set
     */
    public void setUsername(String username) {
    	this.username = username;
    }

	/**
     * @return the judgeResult
     */
    public String getJudgeResult() {
    	return judgeResult;
    }

	/**
     * @param judgeResult the judgeResult to set
     */
    public void setJudgeResult(String judgeResult) {
    	this.judgeResult = judgeResult;
    }

	/**
     * @return the judgeType
     */
    public String getJudgeType() {
    	return judgeType;
    }

	/**
     * @param judgeType the judgeType to set
     */
    public void setJudgeType(String judgeType) {
    	this.judgeType = judgeType;
    }

	/**
     * @return the date
     */
    public String getDate() {
    	return date;
    }

	/**
     * @param date the date to set
     */
    public void setDate(String date) {
    	this.date = date;
    }

	/**
     * @return the memory
     */
    public String getMemory() {
    	return memory;
    }

	/**
     * @param memory the memory to set
     */
    public void setMemory(String memory) {
    	this.memory = memory;
    }

	/**
     * @return the time
     */
    public String getTime() {
    	return time;
    }

	/**
     * @param time the time to set
     */
    public void setTime(String time) {
    	this.time = time;
    }

	/**
     * @return the sourceCode
     */
    public String getSourceCode() {
    	return sourceCode;
    }

	/**
     * @param sourceCode the sourceCode to set
     */
    public void setSourceCode(String sourceCode) {
    	this.sourceCode = sourceCode;
    }

}
