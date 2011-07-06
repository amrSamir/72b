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

@SuppressWarnings("serial")
public class SubmissionData implements Serializable{
	
	private Long submissionID ;
	private String problemTitle;
	private String username ;
	private String judgeUsername ;
	private String problemCode;
	private String judgeType;
	private String judgeResult;
	private String time;
	private String memory;
	private String date;
	public SubmissionData() {
		
	}
	public SubmissionData(String username, String judgeUsername,
            String problemCode, String judgeType, String judgeResult,
            String time, String memory, String date) {
	    super();
	    this.username = username;
	    this.judgeUsername = judgeUsername;
	    this.problemCode = problemCode;
	    this.judgeType = judgeType;
	    this.judgeResult = judgeResult;
	    this.time = time;
	    this.memory = memory;
	    this.date = date;
    }
	public Long getSubmissionID() {
		return submissionID;
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

	public void setSubmissionID(Long submissionID) {
		this.submissionID = submissionID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJudgeUsername() {
		return judgeUsername;
	}
	public void setJudgeUsername(String judgeUsername) {
		this.judgeUsername = judgeUsername;
	}
	public String getProblemCode() {
		return problemCode;
	}
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}
	public String getJudgeType() {
		return judgeType;
	}
	public void setJudgeType(String judgeType) {
		this.judgeType = judgeType;
	}
	public String getJudgeResult() {
		return judgeResult;
	}
	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
