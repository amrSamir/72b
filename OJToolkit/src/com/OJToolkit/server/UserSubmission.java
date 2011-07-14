/**
 * 
 */
package com.OJToolkit.server;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


/**
 * @author 72B
 * Jun 19, 2011
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserSubmission {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long submissionID;

	@Persistent
	private String username;  
	@Persistent
	private String judgeUsername;
	@Persistent
	private String problemCode;
	@Persistent
	private String judgeType;
	@Persistent
	private String judgeResult;
	@Persistent
	private String time;
	@Persistent
	private String memory;
	@Persistent
	private Long date;
	/**
     * @return the submissionID
     */
    public Long getSubmissionID() {
    	return submissionID;
    }
	/**
     * @param submissionID the submissionID to set
     */
    public void setSubmissionID(Long submissionID) {
    	this.submissionID = submissionID;
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
     * @return the judgeUsername
     */
    public String getJudgeUsername() {
    	return judgeUsername;
    }
	/**
     * @param judgeUsername the judgeUsername to set
     */
    public void setJudgeUsername(String judgeUsername) {
    	this.judgeUsername = judgeUsername;
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
     * @return the date
     */
    public Date getDate() {
    	return new Date(this.date);
    }
	/**
     * @param date the date to set
     */
    public void setDate(Date date) {
    	this.date = date.getTime();
    }
	public UserSubmission(String username, String judgeUsername,
            String problemCode, String judgeType, String judgeResult,
            String time, String memory, Date date) {
	    super();
	    this.username = username;
	    this.judgeUsername = judgeUsername;
	    this.problemCode = problemCode;
	    this.judgeType = judgeType;
	    this.judgeResult = judgeResult;
	    this.time = time;
	    this.memory = memory;
	    this.date = date.getTime();
    }
	public UserSubmission() {
	    super();
    }


}
