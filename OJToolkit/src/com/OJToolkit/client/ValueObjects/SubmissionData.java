package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B
 *         Hold the status of the submitted problem
 */
/**
 * @author 72B
 *        July 7, 2011
 */
public class SubmissionData implements Serializable {

	String username;
	String problemCode;
	String problemTitle;
	String judgeType;
	String date;
	String judgeResult;
	String time;
	String memory;
	

	/**
	 * Create an object of the problem status
	 */
	public SubmissionData() {}


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

	

}
