package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B
 *         Holds the Problem Data
 */
/**
 * @author 72B
 *         Apr 23, 2011
 */
public class ProblemData implements Serializable {
	private String url;
	private Long problemID ;
	

	private String problemCode;
	private String problemName;
	private String ojType;

	/**
	 * Creates an a problem object for viewing purpose
	 * 
	 * @param url
	 *            The URL of the problem
	 * @param type
	 *            The category of the problem
	 * @param problemCode
	 *            The code of the problem
	 * @param problemName
	 *            The Title of the problem
	 */
	public ProblemData(String problemCode,
	        String problemName, String url, String ojType) {
		super();
		this.url = url;
		this.problemCode = problemCode;
		this.problemName = problemName;
		this.ojType = ojType;
	}

	/**
	 * Create a problem object for viewing purpose
	 */
	public ProblemData() {
	}
	public void setProblemID(Long problemID) {
		this.problemID = problemID;
	}
	/**
	 * @return The code of the problem
	 */
	public String getProblemCode() {
		return problemCode;
	}

	/**
	 * @param problemCode
	 *            The code of the problem
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
	 * @param problemName
	 *            The Title of the problem
	 */
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}



	/**
	 * @return The URL of the problem
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            The URL of the problem
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * @param ojType the ojType to set
     */
    public void setOjType(String ojType) {
	    this.ojType = ojType;
    }

	/**
     * @return the ojType
     */
    public String getOjType() {
	    return ojType;
    }

	public Long getProblemID() {
		return problemID;
	}
}
