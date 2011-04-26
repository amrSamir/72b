package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B
 * Holds the Problem Data
 */
/**
 * @author 72B
 * Apr 23, 2011
 */
public class ProblemData implements Serializable { 
	private String url;
	private String type;
	private String problemCode;
	private String problemName;

	/**
	 * Creates an a problem object for viewing purpose
	 * @param url The URL of the problem
	 * @param type The category of the problem
	 * @param problemCode The code of the problem
	 * @param problemName The Title of the problem
	 */
	public ProblemData(String url, String type, String problemCode,
			String problemName) {
		super();
		this.url = url;
		this.type = type;
		this.problemCode = problemCode;
		this.problemName = problemName;
	}
	
	/**
	 * Create a problem object for viewing purpose
	 */
	public ProblemData() {
	}


	/**
	 * @return The code of the problem
	 */
	public String getProblemCode() {
		return problemCode;
	}

	/**
	 * @param problemCode The code of the problem
	 */
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	/**
	 * @return The Title of the problem
	 */
	public String getProblemName() {
		return problemName;
	}

	/**
	 * @param problemName The Title of the problem
	 */
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}



	
	/**
	 * @param t The category of the problem
	 */
	public void setType(String t) {
		type = t;
	}

	/**
	 * @return The category of the problem
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return The URL of the problem
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The URL of the problem
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
