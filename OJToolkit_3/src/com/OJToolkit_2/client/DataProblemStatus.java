package com.OJToolkit_2.client;

import java.util.Date;

public class DataProblemStatus {

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
	 
	public DataProblemStatus(String date, String problemLink, String judgeResult,
			String time, String mem) {
		this.date = date;
		this.problemLink = problemLink;
		this.judgeResult = judgeResult;
		this.time = time;
		this.mem = mem;
	}
	 
	
	
	
}
