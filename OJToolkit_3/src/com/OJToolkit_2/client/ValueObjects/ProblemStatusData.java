package com.OJToolkit_2.client.ValueObjects;

import java.io.Serializable;
import java.util.Date;

public class ProblemStatusData implements Serializable{

	/* 				KEY			VALUE
	 * --------	DATE		-->	Date and time for the submitted problem.
	 * --------	PROBLEM		-->	Link of the problem submitted.
	 * --------	RESULT		-->	The response of the judge.
	 * --------	TIME		-->	The running time of the solution.
	 * --------	MEM			-->	The memory used by the solution. */
	
	 String date;
	 String problemLink;
	 String judgeResult;
	 String time;
	 String mem;
	 
	public ProblemStatusData() {
	
		// TODO Auto-generated constructor stub
	}

	public ProblemStatusData(String date, String problemLink, String judgeResult,
			String time, String mem) {
		this.date = date;
		this.problemLink = problemLink;
		this.judgeResult = judgeResult;
		this.time = time;
		this.mem = mem;
	}

	public String getDate() {
		return date;
	}

	public String getProblemLink() {
		return problemLink;
	}

	public String getJudgeResult() {
		return judgeResult;
	}

	public String getTime() {
		return time;
	}

	public String getMem() {
		return mem;
	}
	
	
	 
	
	
	
}
