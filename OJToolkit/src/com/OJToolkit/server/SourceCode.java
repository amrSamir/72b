package com.OJToolkit.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;


/**
 * @author 72B July 9, 2011
 */ 
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SourceCode {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long sourceCodeID;

	@Persistent
	private Long submissionID;
	

	@Persistent 
	private Text sourceCode;
	
	@Persistent
	private boolean isVisible; 

	
		public SourceCode(){}


	public SourceCode(Long submissionID, String sourceCode, boolean isVisible) {
	        super();
	        this.submissionID = submissionID;
	        this.sourceCode = new Text(sourceCode);
	        this.isVisible = isVisible;
        }


	/**
     * @return the sourceCodeID
     */
    public Long getSourceCodeID() {
    	return sourceCodeID;
    }


	/**
     * @param sourceCodeID the sourceCodeID to set
     */
    public void setSourceCodeID(Long sourceCodeID) {
    	this.sourceCodeID = sourceCodeID;
    }


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
     * @return the isVisible
     */
    public boolean isVisible() {
    	return isVisible;
    }


	/**
     * @param isVisible the isVisible to set
     */
    public void setVisible(boolean isVisible) {
    	this.isVisible = isVisible;
    }





	public String getSourceCode() {
		return sourceCode.getValue();
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = new Text(sourceCode);
	}

	
	 
}
