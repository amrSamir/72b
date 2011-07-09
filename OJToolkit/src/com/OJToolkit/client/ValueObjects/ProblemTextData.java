package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;


public class ProblemTextData implements Serializable{

	
	private String judgeType;

	
	private String problemCode;

	
	private String isDividable;
	
	
	private String fullText;
	
	
	private String problemStatement;
	
	
	private String inputConstraints;
	
	
	private String outputConstraints;
	
	
	private String IOTestCases;

	public ProblemTextData() {
    }

	public ProblemTextData(String judgeType, String problemCode,
            String isDividable, String fullString, String problemStatement,
            String inputConstraints, String outputConstraints, String iOTestCases) {
	    super();
	    this.judgeType = judgeType;
	    this.problemCode = problemCode;
	    this.isDividable = isDividable;
	    this.fullText = fullString;
	    this.problemStatement = problemStatement;
	    this.inputConstraints = inputConstraints;
	    this.outputConstraints = outputConstraints;
	    this.IOTestCases = iOTestCases;
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
     * @return the isDividable
     */
    public String getIsDividable() {
    	return isDividable;
    }

	/**
     * @param isDividable the isDividable to set
     */
    public void setIsDividable(String isDividable) {
    	this.isDividable = isDividable;
    }

	/**
     * @return the fullString
     */
    public String getFullText() {
    	return fullText;
    }

	/**
     * @param fullString the fullString to set
     */
    public void setFullText(String fullText) {
    	this.fullText = fullText;
    }

	/**
     * @return the problemStatement
     */
    public String getProblemStatement() {
    	return problemStatement;
    }

	/**
     * @param problemStatement the problemStatement to set
     */
    public void setProblemStatement(String problemStatement) {
    	this.problemStatement = problemStatement;
    }

	/**
     * @return the inputConstraints
     */
    public String getInputConstraints() {
    	return inputConstraints;
    }

	/**
     * @param inputConstraints the inputConstraints to set
     */
    public void setInputConstraints(String inputConstraints) {
    	this.inputConstraints = inputConstraints;
    }

	/**
     * @return the outputConstraints
     */
    public String getOutputConstraints() {
    	return outputConstraints;
    }

	/**
     * @param outputConstraints the outputConstraints to set
     */
    public void setOutputConstraints(String outputConstraints) {
    	this.outputConstraints = outputConstraints;
    }

	/**
     * @return the iOTestCases
     */
    public String getIOTestCases() {
    	return IOTestCases;
    }

	/**
     * @param iOTestCases the iOTestCases to set
     */
    public void setIOTestCases(String iOTestCases) {
    	IOTestCases = iOTestCases;
    }
	
	

}
