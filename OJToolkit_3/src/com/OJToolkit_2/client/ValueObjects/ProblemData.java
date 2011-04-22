package com.OJToolkit_2.client.ValueObjects;

import java.io.Serializable;

public class ProblemData implements Serializable {

	private String url;
	private String type;
	private String problemCode;
	private String problemName;

	public ProblemData(String url, String type, String problemCode,
			String problemName) {
		super();
		this.url = url;
		this.type = type;
		this.problemCode = problemCode;
		this.problemName = problemName;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public ProblemData(String u) {
		url = u;
	}

	public ProblemData() {
	}

	public void setType(String t) {
		type = t;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
