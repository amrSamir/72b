package com.OJToolkit.client.ValueObjects;

import java.io.Serializable;

/**
 * @author 72B
 *         Hold the status of the submitted problem
 */
/**
 * @author 72B
 *         Apr 23, 2011
 */
public class ProblemStatusData implements Serializable {

	String date;
	String problemLink;
	String judgeResult;
	String time;
	String mem;

	/**
	 * Create an object of the problem status
	 */
	public ProblemStatusData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create an object of the problem status
	 * 
	 * @param date
	 *            Date and time for the submitted problem
	 * @param problemLink
	 *            Link of the problem submitted
	 * @param judgeResult
	 *            The response of the judge
	 * @param time
	 *            The running time of the solution
	 * @param mem
	 *            The memory used by the solution
	 */
	public ProblemStatusData(String date, String problemLink,
	        String judgeResult, String time, String mem) {
		this.date = date;
		this.problemLink = problemLink;
		this.judgeResult = judgeResult;
		this.time = time;
		this.mem = mem;
	}

	/**
	 * @return Date and time for the submitted problem
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return Link of the problem submitted
	 */
	public String getProblemLink() {
		return problemLink;
	}

	/**
	 * @return The response of the judge
	 */
	public String getJudgeResult() {
		return judgeResult;
	}

	/**
	 * @return The running time of the solution
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return The memory used by the solution
	 */
	public String getMem() {
		return mem;
	}

}
