package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProblemText {
	@PrimaryKey 
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long probID;

	@Persistent
	private String judgeType;

	@Persistent
	private String problemCode;

	@Persistent
	private String isDividable;
	
	@Persistent
	private Text fullText;
	
	@Persistent
	private Text problemStatement;
	 
	@Persistent
	private Text inputConstraints; 
	
	@Persistent
	private Text outputConstraints;
	
	@Persistent
	private Text IOTestCases;

	public ProblemText(String judgeType, String problemCode,
            String isDividable, String fullText, String problemStatement,
            String inputConstraints, String outputConstraints, String iOTestCases) {
	    super();
	    this.judgeType = judgeType;
	    this.problemCode = problemCode;
	    this.isDividable = isDividable;
	    this.fullText = new Text(fullText);
	    this.problemStatement = new Text(problemStatement);
	    this.inputConstraints = new Text(inputConstraints);
	    this.outputConstraints = new Text(outputConstraints);
	    this.IOTestCases = new Text(iOTestCases);
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
     * @return the fullText
     */
    public Text getFullText() {
    	return fullText;
    }

	/**
     * @param fullText the fullText to set
     */
    public void setFullText(String fullText) {
    	this.fullText = new Text(fullText);
    }

	/**
     * @return the problemStatement
     */
    public Text getProblemStatement() {
    	return problemStatement;
    }

	/**
     * @param problemStatement the problemStatement to set
     */
    public void setProblemStatement(String problemStatement) {
    	this.problemStatement = new Text(problemStatement);
    }

	/**
     * @return the inputConstraints
     */
    public Text getInputConstraints() {
    	return inputConstraints;
    }

	/**
     * @param inputConstraints the inputConstraints to set
     */
    public void setInputConstraints(String inputConstraints) {
    	this.inputConstraints = new Text(inputConstraints);
    }

	/**
     * @return the outputConstraints
     */
    public Text getOutputConstraints() {
    	return outputConstraints;
    }

	/**
     * @param outputConstraints the outputConstraints to set
     */
    public void setOutputConstraints(String outputConstraints) {
    	this.outputConstraints = new Text(outputConstraints);
    }

	/**
     * @return the iOTestCases
     */
    public Text getIOTestCases() {
    	return IOTestCases;
    }

	/**
     * @param iOTestCases the iOTestCases to set
     */
    public void setIOTestCases(String iOTestCases) {
    	IOTestCases = new Text(iOTestCases);
    }
	
	

}
